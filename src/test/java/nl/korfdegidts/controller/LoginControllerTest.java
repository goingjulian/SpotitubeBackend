/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/16/18 3:33 PM
 */

package nl.korfdegidts.controller;

import nl.korfdegidts.dto.TokenDTO;
import nl.korfdegidts.entity.UserCredentials;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @InjectMocks
    private LoginController sut = new LoginController();

    @Mock
    private ILoginService loginService;

    private UserCredentials validCredentials;

    @Before
    public void setUp() {
        validCredentials = new UserCredentials("testu", "pass");
    }

    @Test
    public void testIfResponse401IsReturnedWhenCredentialsAreIncorrect() throws UserNotFoundException {
        UserCredentials invalidCredentials = new UserCredentials("notValid", "pass");
        Mockito.when(loginService.getTokenDTOFromCredentials(invalidCredentials)).thenThrow(new UserNotFoundException());

        Response response = sut.login(invalidCredentials);
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testIfResponse201IsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
        Mockito.when(
                loginService.getTokenDTOFromCredentials(validCredentials)
        ).thenReturn(
                new TokenDTO(validCredentials.getUser(), "1234-1234-1234")
        );

        Response response = sut.login(validCredentials);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testIfCorrectTokenIsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
        Mockito.when(
                loginService.getTokenDTOFromCredentials(validCredentials)
        ).thenReturn(
                new TokenDTO(validCredentials.getUser(), "2222-2222")
        );

        Response response = sut.login(validCredentials);

        TokenDTO token = (TokenDTO) response.getEntity();

        assertEquals("2222-2222", token.getToken());
    }

    @Test
    public void testIfCorrectUsernameIsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
        Mockito.when(
                loginService.getTokenDTOFromCredentials(validCredentials)
        ).thenReturn(
                new TokenDTO(validCredentials.getUser(), "2222-2222")
        );

        Response response = sut.login(validCredentials);

        TokenDTO token = (TokenDTO) response.getEntity();

        assertEquals(validCredentials.getUser(), token.getUser());

    }


}