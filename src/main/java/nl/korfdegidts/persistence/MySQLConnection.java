/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.persistence;

import nl.korfdegidts.exception.ConfigurationNotFoundException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnection implements IDBConnection {
    private Properties props;

    public MySQLConnection() {
        props = new Properties();
    }

    @Override
    public Connection getConnection() {
        try {
            props.load(getClass().getResourceAsStream("/database.properties"));
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
