package nl.korfdegidts.authentication;

import nl.korfdegidts.exception.UserNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginServiceHardCodedTest {

    @Rule
    private ExpectedException thrown = ExpectedException.none();

    @Test
    public void test() {
        thrown.expect(UserNotFoundException.class);
        //Om klassen tomEE te kunnen benaderen in test, gebruik Apache CXF Runtime JAX RS Frontend
    }

}