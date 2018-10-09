package nl.korfdegidts.datasource;

import java.sql.Connection;

public interface IDBConnection {

    void connect();

    Connection getConnection();
}
