package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.authentication.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials credentials) {

        User userToCompare = new User(new LoginCredentials("julian", "pass"));

        if (credentialsIdentical(credentials, userToCompare)) {

            return Response.status(Response.Status.CREATED).entity(userToCompare.getToken()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    private boolean credentialsIdentical(LoginCredentials credentials, User userToCompare) {
        return credentials.getUser().equals(userToCompare.getCredentials().getUser())
                && credentials.getPassword().equals(userToCompare.getCredentials().getPassword());
    }
}
