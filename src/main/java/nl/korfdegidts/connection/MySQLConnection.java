package nl.korfdegidts.connection;

import nl.korfdegidts.exception.ConfigurationNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection implements IDBConnection {
    public static final String PATH_DATABASE_PROPERTIES = "../../../database.properties";
    private Properties props;
    private InputStream in;

    public MySQLConnection() {
        props = new Properties();

        try {
            in = new FileInputStream(PATH_DATABASE_PROPERTIES);
        } catch (FileNotFoundException e) {
            throw new ConfigurationNotFoundException();
        }
    }

    @Override
    public Connection getConnection() {
        try {
            props.load(in);
            in.close();
        } catch (IOException e) {
            throw new ConfigurationNotFoundException();
        }

        String driver = props.getProperty("jdbc.driver");
        String url = props.getProperty("jdbc.url");
        String user = props.getProperty("jdbc.user");
        String password = props.getProperty("jdbc.password");

        Connection connection = null;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
