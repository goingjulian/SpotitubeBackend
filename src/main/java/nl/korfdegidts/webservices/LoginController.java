package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private User userToCompare = new User(new LoginCredentials("julian", "pass"));

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials credentials) {

        if (credentialsCorrect(credentials, userToCompare)) {

            return Response.status(Response.Status.CREATED).entity(userToCompare.getToken()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    boolean credentialsCorrect(LoginCredentials credentials, User userToCompare) {
        return credentials.getUser().equals(userToCompare.getCredentials().getUser())
                && credentials.getPassword().equals(userToCompare.getCredentials().getPassword());
    }
}
