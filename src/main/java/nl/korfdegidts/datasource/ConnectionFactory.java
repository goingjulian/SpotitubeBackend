package nl.korfdegidts.datasource;

import javax.inject.Inject;

public class ConnectionFactory {
    private MySQLConnection mySQLConnection;

    @Inject
    private void setMySQLConnection(MySQLConnection mySQLConnection) {
        this.mySQLConnection = mySQLConnection;
    }

    public MySQLConnection getMysqlConnection() {
        return mySQLConnection;
    }
}
