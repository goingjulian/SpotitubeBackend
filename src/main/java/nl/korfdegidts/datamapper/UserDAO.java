package nl.korfdegidts.datamapper;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DataMapper {

    public User getUserFromCredentials(UserCredentials credentials) throws UserNotFoundException {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT username, password " +
                                "FROM user " +
                                "WHERE username = ? " +
                                "AND password = ? "
                )
        ) {
            stmnt.setString(1, credentials.getUser());
            stmnt.setString(2, credentials.getPassword());
            ResultSet rs = stmnt.executeQuery();
            rs.last();
            if (resultsEmpty(rs)) {
                throw new UserNotFoundException();
            } else {
                rs.first();
                return mapResult(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserFromToken(String token) throws UserNotFoundException {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT u.username, password " +
                                "FROM user u " +
                                "INNER JOIN token t " +
                                "ON u.username = t.username " +
                                "WHERE t.token = ? "
                )
        ) {
            stmnt.setString(1, token);
            ResultSet rs = stmnt.executeQuery();

            rs.next();

            if (resultsEmpty(rs)) {
                throw new UserNotFoundException();
            } else {
                rs.first();
                return mapResult(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistNewUser(User user) {
        try (
                Connection connection = factory.getMysqlConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement("INSERT INTO user (user, password) VALUES (?, ?)")

        ) {
            stmnt.setString(1, user.getCredentials().getUser());
            stmnt.setString(2, user.getCredentials().getPassword());
            stmnt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected User mapResult(ResultSet rs) throws SQLException {
        return new User(
                new UserCredentials(
                        rs.getString("username"),
                        rs.getString("password")
                )
        );
    }

    private boolean resultsEmpty(ResultSet rs) throws SQLException {
        rs.last();
        return rs.getRow() <= 0;
    }


}