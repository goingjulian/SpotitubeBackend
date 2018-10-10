package nl.korfdegidts.datasource;

public class ConnectionFactory {

    public MySQLConnection getMysqlConnection() {
        return new MySQLConnection();
    }
}
