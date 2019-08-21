/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:42 AM
 */

package nl.korfdegidts.persistence;

import nl.korfdegidts.entity.Token;
import nl.korfdegidts.entity.UserCredentials;
import nl.korfdegidts.exception.AllTokensOccupiedException;
import nl.korfdegidts.tokenGenerator.TokenGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenDAO extends DAO {

    private static final int TIME_IM_MS = 7200000;
    private static final int MAX_TRIES = 3;

    public Token getNewUserToken(UserCredentials credentials) {
        TokenGenerator generator = new TokenGenerator();
        deleteOldTokens(credentials);

        String tokenStr = generator.getUniqueToken();

        int tries = 0;
        while (tokenAlreadyTaken(tokenStr)) {

            if (tries >= MAX_TRIES) {
                throw new AllTokensOccupiedException(
                        "All possible token combinations are already taken, " +
                                "your app must be really popular so the tokens " +
                                "you generate do not provide enough possible combinations"
                );
            } else {
                tokenStr = generator.getUniqueToken();
                tries++;
            }

        }

        Token token = new Token(credentials.getUser(), tokenStr, getExpiryDate());

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
                        "DELETE FROM token WHERE username = ? AND expiryDate <= ?"
                )
        ) {
            stmnt.setString(1, credentials.getUser());
            stmnt.setLong(2, System.currentTimeMillis());
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

    private boolean resultsEmpty(ResultSet rs) throws SQLException {
        rs.last();
        return rs.getRow() <= 0;
    }

    @Override
    protected Token mapResult(ResultSet rs) {
        return null;
    }
}
