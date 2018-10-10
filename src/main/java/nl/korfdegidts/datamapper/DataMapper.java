package nl.korfdegidts.datamapper;

import nl.korfdegidts.connection.ConnectionFactory;
import nl.korfdegidts.entity.IEntity;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataMapper {

    protected ConnectionFactory factory;

    @Inject
    public void setFactory(ConnectionFactory factory) {
        this.factory = factory;
    }

    protected abstract IEntity mapResult(ResultSet rs) throws SQLException;
}
