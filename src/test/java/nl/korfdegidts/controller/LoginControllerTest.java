//package nl.korfdegidts.controller;
//
//import nl.korfdegidts.authentication.UserCredentials;
//import nl.korfdegidts.dto.TokenDTO;
//import nl.korfdegidts.entity.User;
//import nl.korfdegidts.exception.UserNotFoundException;
//import nl.korfdegidts.service.ILoginService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import javax.ws.rs.core.Response;
//
//import static org.junit.Assert.assertEquals;
//
//@RunWith(MockitoJUnitRunner.class)
//public class LoginControllerTest {
//
//    @InjectMocks
//    private LoginController sut = new LoginController();
//
//    @Mock
//    private ILoginService loginService;
//
//    private User validUser;
//    private UserCredentials validCredentials;
//
//    @Before
//    public void setUp() throws UserNotFoundException {
//        validCredentials = new UserCredentials("testu", "pass");
//        validUser = new User(validCredentials, "1234-1234-1234");
//    }
//
//    @Test
//    public void testIfResponse401IsReturnedWhenCredentialsAreIncorrect() throws UserNotFoundException {
//        UserCredentials invalidCredentials = new UserCredentials("notValid", "pass");
//        Mockito.when(loginService.getUserFromCredentials(invalidCredentials)).thenThrow(new UserNotFoundException());
//
//        Response response = sut.login(invalidCredentials);
//        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
//    }
//
//    @Test
//    public void testIfResponse201IsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
//        Mockito.when(loginService.getUserFromCredentials(validCredentials)).thenReturn(validUser);
//
//        Response response = sut.login(validCredentials);
//        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
//    }
//
//    @Test
//    public void testIfCorrectTokenIsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
//        Mockito.when(loginService.getUserFromCredentials(validCredentials)).thenReturn(validUser);
//
//        String stringToken = "1234-1234-1234";
//
//        TokenDTO validToken = new TokenDTO(validUser.getCredentials().getUser(), stringToken);
//
//        TokenDTO retunedToken = getRetunedToken(stringToken);
//
//        assertEquals(retunedToken.getToken(), validToken.getToken());
//    }
//
//    @Test
//    public void testIfCorrectUsernameIsReturnedWhenCredentialsAreCorrect() throws UserNotFoundException {
//        Mockito.when(loginService.getUserFromCredentials(validCredentials)).thenReturn(validUser);
//
//        String stringToken = "1234-1234-1234";
//
//        TokenDTO validToken = new TokenDTO(validUser.getCredentials().getUser(), stringToken);
//
//        TokenDTO retunedToken = getRetunedToken(stringToken);
//
//        assertEquals(retunedToken.getUser(), validToken.getUser());
//
//    }
//
//    private TokenDTO getRetunedToken(String tokenStr) {
//        Response response = sut.login(validCredentials);
//
//        validUser.setToken(tokenStr);
//
//        return (TokenDTO) response.getEntity();
//    }
//
//
//}