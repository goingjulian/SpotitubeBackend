/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/23/18 4:10 PM
 */

package nl.korfdegidts.persistence;

import java.sql.Connection;

public interface IDBConnection {

    Connection getConnection();
}
