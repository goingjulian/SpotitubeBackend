/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/14/18 3:28 PM
 */

package nl.korfdegidts.entity;

public class Token implements IEntity {
    private String username;
    private String token;
    private long expiryDate;

    public Token(String username, String token, long expiryDate) {
        this.username = username;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }

    public long getExpiryDate() {
        return expiryDate;
    }
}
