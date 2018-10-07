package nl.korfdegidts.datamapper;

import nl.korfdegidts.datasource.IDBConnection;
import nl.korfdegidts.datasource.MySQLConnection;

public abstract class DataMapper {
    protected IDBConnection connection = new MySQLConnection();

//    @Inject
//    public void setConnection(IDBConnection connection) {
//        this.connection = connection;
//    }
}
