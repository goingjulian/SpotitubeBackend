package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.AllPlaylistsResponse;
import nl.korfdegidts.components.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class SpotitubeBackend {

    //Replace with db
    User hardCodedUser = new User(new LoginCredentials("julian", "pass"));

    //Replace with db
    public SpotitubeBackend() {
        hardCodedUser.addPlaylist("Cool Songs");
        hardCodedUser.addPlaylist("Hipster Songs");
        hardCodedUser.addPlaylist("Sad Songs");
        hardCodedUser.addPlaylist("Great Songs");
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginCredentials credentials) {

        User foundUser = getUserFromCredentials(credentials);

        if (foundUser != null) {

            return Response.status(Response.Status.CREATED).entity(foundUser.getTokenObject()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Path("playlists")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylistsFromUser(@QueryParam("token") String token) {
        User user = getUserFromToken(token);

        if (user != null) {
            AllPlaylistsResponse allPlaylistsResponse = new AllPlaylistsResponse(user);

            return Response.status(Response.Status.OK).entity(allPlaylistsResponse).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    private User getUserFromToken(String token) {
        //Replace with db
        if (hardCodedUser.getTokenObject().getToken().equals(token)) return hardCodedUser;

        return null;
    }

    User getUserFromCredentials(LoginCredentials credentials) {
        //replace with db
        if (credentials.getUser().equals(hardCodedUser.getCredentials().getUser())
                && credentials.getPassword().equals(hardCodedUser.getCredentials().getPassword()))
            return hardCodedUser;

        return null;
    }
}
