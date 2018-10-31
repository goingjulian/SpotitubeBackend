/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/31/18 10:50 PM
 */

package nl.korfdegidts.controller;

import nl.korfdegidts.service.ILoginService;
import nl.korfdegidts.service.PlaylistService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @InjectMocks
    private PlaylistController sut;

    @Mock
    private ILoginService loginService;

    @Mock
    private PlaylistService playlistService;

//    @Test
//    public void testThatStatusForbiddenIsReturnedWhenTokenIsInvalid() throws UserNotFoundException {
//        Mockito.when(loginService.getUserFromToken("0000-0000-0000")).thenThrow(
//                new UserNotFoundException()
//        );
//
//        Response response = sut.getAllPlaylistsFromUser("0000-0000-0000");
//
//        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
//    }
//
//    @Test
//    public void testThatStatusOKIsReturnedWhenTokenIsValid() throws UserNotFoundException {
//        User user = new User(new UserCredentials("test", "pass"), Role.ADMIN);
//
//        Mockito.when(loginService.getUserFromToken("1234-1234-1234")).thenReturn(user);
//
//        Mockito.when(playlistService.getAllPlaylistsFromUser(user)).thenReturn(
//                new PlaylistsDTO(Arrays.asList(
//                        new Playlist(0, "test", false),
//                        new Playlist(1, "test2", false)
//                ), 0));
//
//        sut.setPlaylistService(playlistService);
//
//        Response response = sut.getAllPlaylistsFromUser("1234-1234-1234");
//
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }

}