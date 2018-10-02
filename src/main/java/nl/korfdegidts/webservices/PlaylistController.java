package nl.korfdegidts.webservices;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.AllPlaylistsResponse;
import nl.korfdegidts.components.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {
    private User hardCodedUser = new User(new LoginCredentials("julian", "pass"));

    public PlaylistController() {
        hardCodedUser.createPlaylist("Cool Songs");
        hardCodedUser.createPlaylist("Angry Songs");
        hardCodedUser.createPlaylist("Sad Songs");
        hardCodedUser.createPlaylist("Great Songs");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{token}")
    public Response getAllPlaylistsFromUser(@PathParam("token") String token) {
        if (token.equals(hardCodedUser.getToken().getStringToken())) {

            AllPlaylistsResponse allPlaylistsResponse = new AllPlaylistsResponse(hardCodedUser);

            return Response.status(Response.Status.OK).entity(allPlaylistsResponse).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
