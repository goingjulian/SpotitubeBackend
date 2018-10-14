/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/14/18 3:28 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.dto.TokenDTO;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

public interface ILoginService {
    TokenDTO getTokenDTOFromCredentials(UserCredentials credentials) throws UserNotFoundException;

    User getUserFromToken(String token) throws UserNotFoundException;
}
