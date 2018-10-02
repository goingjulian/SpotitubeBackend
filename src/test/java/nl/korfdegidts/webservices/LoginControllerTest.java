package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {
    private LoginController controller;

    @Before
    public void setUp() {
        controller = new LoginController();
    }

    @Test
    public void testIfLoginWorksWithHardcodedCredentials() {
        LoginCredentials providedCredentials = new LoginCredentials("john", "secret");

        User userToCompare = new User(providedCredentials);

        assertTrue(controller.credentialsCorrect(providedCredentials, userToCompare));
    }

    @Test
    public void testIfLoginFailsIfCredentialsAreNotCorrect() {
        LoginCredentials providedCredentials = new LoginCredentials("john", "secret");

        User userToCompare = new User(new LoginCredentials("peter", "password"));

        assertFalse(controller.credentialsCorrect(providedCredentials, userToCompare));
    }
}