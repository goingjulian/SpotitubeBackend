/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 12:10 PM
 */

package nl.korfdegidts.connection;

import nl.korfdegidts.exception.ConfigurationNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection implements IDBConnection {
    public static final String PATH_DATABASE_PROPERTIES = "../../../database.properties";
    private Properties props;
//    private InputStream in;

    public MySQLConnection() {
        props = new Properties();

//        try {
//            in = new FileInputStream(PATH_DATABASE_PROPERTIES);
//        } catch (FileNotFoundException e) {
//            throw new ConfigurationNotFoundException();
//        }
    }

    @Override
    public Connection getConnection() {
        try {
            props.load(getClass().getResourceAsStream("/database.properties"));
//            in.close();
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
