package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SpotitubeBackendTest {
    private SpotitubeBackend sut = new SpotitubeBackend();

    @Test
    public void testIfCorrectUserIsReturnedWhenCredentialsAreCorrect() {
        LoginCredentials credentials = new LoginCredentials("hans", "test");

        User validTestUser = new User(credentials);
        sut.hardCodedUser = validTestUser;

        assertEquals(validTestUser, sut.getUserFromCredentials(credentials));
    }

    @Test
    public void testIfNullIsReturnedIfUserDoesNotExcist() {
        LoginCredentials credentials = new LoginCredentials("not", "valid");

        sut.hardCodedUser = new User(new LoginCredentials("valid", "thistime"));

        assertNull(sut.getUserFromCredentials(credentials));
    }

    @Test
    public void testIfUserIsReturnedIfTokenDoesMatchUser() {
        assertEquals(sut.hardCodedUser, sut.getUserFromToken("1234-1234-1234"));
    }

    @Test
    public void testIfNullIsReturnedIfTokenDoesNotMatchUser() {
        assertEquals(sut.hardCodedUser, sut.getUserFromToken("1234-1234-1234"));
    }
}