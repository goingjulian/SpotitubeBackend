/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/14/18 3:28 PM
 */

package nl.korfdegidts.authentication;

import java.util.UUID;

public class TokenGenerator {

    public String getUniqueToken() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
