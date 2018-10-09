package nl.korfdegidts.datamapper;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.entity.Token;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class TokenDAO extends DataMapper {

    private static final int TOKEN_PAIR_AMOUNT = 4;
    private static final int MAX_PAIR_VALUE = 9999;

    public Token getNewUserToken(UserCredentials credentials) {
        deleteOldTokens(credentials);

        Token token = new Token(credentials.getUser(), generateUniqueToken(), getExpiryDate());

        System.out.println(token.getToken());
        System.out.println(token.getExpiryDate());
        addNewTokenToUser(token);
        return token;
    }

    private void addNewTokenToUser(Token token) {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "INSERT INTO token (username, token, expiryDate) VALUES (?, ?, ?)"
                )
        ) {

            stmnt.setString(1, token.getUsername());
            stmnt.setString(2, token.getToken());
            stmnt.setLong(3, token.getExpiryDate());

            stmnt.execute();
            System.out.println("stmnt executed");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteOldTokens(UserCredentials credentials) {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "DELETE FROM token WHERE username = ?"
                )
        ) {
            stmnt.setString(1, credentials.getUser());
            stmnt.execute();
            System.out.println("stmnt2 executed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private long getExpiryDate() {
        long currentTime = System.currentTimeMillis();
        long expiryDate = currentTime + (2 * (60 * 60));
        System.out.println("exp");
        System.out.println(expiryDate - currentTime);

        System.out.println(expiryDate);
        return expiryDate;

    }

    private boolean tokenAlreadyTaken(String token) {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
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

        String token = randomToken.toString();

        if (tokenAlreadyTaken(token)) {
            generateUniqueToken();
            return null;
        } else {
            return token;
        }
    }

    private boolean resultsEmpty(ResultSet rs) throws SQLException {
        rs.last();
        return rs.getRow() <= 0;
    }

    @Override
    protected Token mapResult(ResultSet rs) {
//        return new Token(
//                rs.getInt("user_id"),
//                rs.getString("token"),
//                rs.getDate("expiryDate")
//        );
        return null;
    }
}
