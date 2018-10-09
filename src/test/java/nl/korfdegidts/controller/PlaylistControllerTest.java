package nl.korfdegidts.controller;

import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

    @InjectMocks
    private PlaylistController sut;

    @Mock
    private ILoginService loginService;

    @Test
    public void testThatStatusForbiddenIsReturnedWhenTokenIsInvalid() throws UserNotFoundException {
        Mockito.when(loginService.getUserFromToken("0000-0000-0000")).thenThrow(
                new UserNotFoundException()
        );

        Response response = sut.getAllPlaylistsFromUser("0000-0000-0000");

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

//    @Test
//    public void testThatStatusOKIsReturnedWhenTokenIsValid() throws UserNotFoundException {
//        Mockito.when(loginService.getUserFromToken("1234-1234-1234")).thenReturn(
//                new User(new UserCredentials("test", "pass"))
//        );
//
//        Response response = sut.getAllPlaylistsFromUser("1234-1234-1234");
//
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//    }

}