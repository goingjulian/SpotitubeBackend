/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 11/1/18 11:54 AM
 */

package nl.korfdegidts.authentication;

import nl.korfdegidts.entity.User;

public class AuthenticatedUser {
    private static User authenticatedUser;

    public static User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static void setAuthenticatedUser(User authenticatedUser) {
        AuthenticatedUser.authenticatedUser = authenticatedUser;
    }
}
