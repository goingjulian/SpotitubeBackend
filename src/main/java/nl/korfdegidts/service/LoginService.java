/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/9/18 10:43 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.datamapper.TokenDAO;
import nl.korfdegidts.datamapper.UserDAO;
import nl.korfdegidts.dto.TokenDTO;
import nl.korfdegidts.entity.Token;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

import javax.inject.Inject;

public class LoginService implements ILoginService {

    private UserDAO dao;
    private TokenDAO tokenDAO;

    @Inject
    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Inject
    public void setTokenDAO(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public User getUserFromToken(String token) throws UserNotFoundException {
        return dao.getUserFromToken(token);
    }

    @Override
    public TokenDTO getTokenDTOFromCredentials(UserCredentials credentials) throws UserNotFoundException {
        User user = dao.getUserFromCredentials(credentials);
        Token token = tokenDAO.getNewUserToken(credentials);
        return new TokenDTO(user.getCredentials().getUser(), token.getToken());
    }


}
