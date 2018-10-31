/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/31/18 10:50 PM
 */

package nl.korfdegidts.entity;

import nl.korfdegidts.Authentication.Role;

import java.security.Principal;

public class User implements IEntity, Principal {
    private UserCredentials credentials;
    private String token;
    private Role role;

    public User() {
    }

    public User(UserCredentials credentials, String token, Role role) {
        this.credentials = credentials;
        this.token = token;
        this.role = role;
    }

    public User(UserCredentials credentials, Role role) {
        this.credentials = credentials;
        this.role = role;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return credentials.getUser();
    }
}
