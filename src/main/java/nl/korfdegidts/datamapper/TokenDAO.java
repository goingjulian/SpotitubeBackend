package nl.korfdegidts.datamapper;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.entity.Token;
import nl.korfdegidts.exception.AllTokensOccupiedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TokenDAO extends DataMapper {

    private static final int TOKEN_PAIR_AMOUNT = 4;
    private static final int MAX_PAIR_VALUE = 9999;
    private static final int TIME_IM_MS = 7200000;
    private static final int MAX_TRIES = 3;

    public Token getNewUserToken(UserCredentials credentials) {
        deleteOldTokens(credentials);

        String tokenStr = generateUniqueToken();

        int tries = 0;
        while (tokenAlreadyTaken(tokenStr)) {

            if (tries >= MAX_TRIES) {
                throw new AllTokensOccupiedException(
                        "No unique tokens found, make sure that the number range is sufficient enough"
                );
            } else {
                tokenStr = generateUniqueToken();
                tries++;
            }

        }

        Token token = new Token(credentials.getUser(), generateUniqueToken(), getExpiryDate());

        addNewTokenToUser(token);

        return token;
    }

    private void addNewTokenToUser(Token token) {
        try (
                Connection connection = factory.getDBConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "INSERT INTO token (username, token, expiryDate) VALUES (?, ?, ?)"
                )
        ) {

            stmnt.setString(1, token.getUsername());
            stmnt.setString(2, token.getToken());
            stmnt.setLong(3, token.getExpiryDate());

            stmnt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOldTokens(UserCredentials credentials) {
        try (
                Connection connection = factory.getDBConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "DELETE FROM token WHERE username = ?"
                )
        ) {
            stmnt.setString(1, credentials.getUser());
            stmnt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getExpiryDate() {
        long currentTime = System.currentTimeMillis();
        return currentTime + TIME_IM_MS;

    }

    private boolean tokenAlreadyTaken(String token) {
        try (
                Connection connection = factory.getDBConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT token FROM token WHERE token = ?"
                )
        ) {
            stmnt.setString(1, token);
            ResultSet rs = stmnt.executeQuery();

            return !resultsEmpty(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateUniqueToken() {

        Random rand = new Random();

        StringBuilder randomToken = new StringBuilder();

        for (int i = 0; i < TOKEN_PAIR_AMOUNT; i++) {
            randomToken.append(Integer.toString(rand.nextInt(MAX_PAIR_VALUE)));
        }

        return randomToken.toString();

    }

    private boolean resultsEmpty(ResultSet rs) throws SQLException {
        rs.last();
        return rs.getRow() <= 0;
    }

    @Override
    protected Token mapResult(ResultSet rs) {
        return null;
    }
}
