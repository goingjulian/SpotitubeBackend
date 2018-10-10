package nl.korfdegidts.connection;

public class ConnectionFactory {

    public IDBConnection getDBConnection() {
        return new MySQLConnection();
    }
}
