/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/10/18 9:05 PM
 */

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
