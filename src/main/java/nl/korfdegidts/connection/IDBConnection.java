/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/10/18 9:05 PM
 */

package nl.korfdegidts.connection;

import java.sql.Connection;

public interface IDBConnection {

    Connection getConnection();
}
