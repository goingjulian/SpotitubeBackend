package nl.korfdegidts.datasource;

import java.sql.Connection;
import java.sql.ResultSet;

public interface IDBConnection {

    void connect();

    Connection getConnection();

    ResultSet performSQL(String sql);
}
