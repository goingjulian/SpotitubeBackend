package nl.korfdegidts.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements IDBConnection {
    private Connection connection = null;

    public MySQLConnection() {
        connect();
    }

    @Override
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/spotitube", "root", "root");
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
