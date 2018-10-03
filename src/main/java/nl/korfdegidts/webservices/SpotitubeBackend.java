package nl.korfdegidts.webservices;

import nl.korfdegidts.Responses.AllPlaylistsResponse;
import nl.korfdegidts.Responses.AllTracksResponse;
import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.components.Song;
import nl.korfdegidts.components.Track;
import nl.korfdegidts.components.User;
import nl.korfdegidts.components.Video;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class SpotitubeBackend {

    /**
     * Test poulation, replace asap
     */
    //Replace with db
    User hardCodedUser = new User(new LoginCredentials("julian", "pass"));

    //Replace with db
    public SpotitubeBackend() {
        hardCodedUser.addPlaylist(0, "Cool Songs");
        hardCodedUser.addPlaylist(1, "Hipster Songs");
        hardCodedUser.addPlaylist(2, "Sad Songs");
        hardCodedUser.addPlaylist(3, "Great Songs");

        Track sunshineSong = new Song(0, "Sunshine", "Pietje Pietersen",
                254, "Songs of shine", 3, true);

        Track moonshineVideo = new Video(0, "Moonshine", "Pietje Pietersen",
                120, 500, "A movie", "02-02-2016", false);

        Track rainVideo = new Video(0, "Rain", "Pietje Pietersen",
                500, 1, "Another movie", "08-08-1991", true);

        hardCodedUser.getPlaylists().get(0).addTrack(sunshineSong);
        hardCodedUser.getPlaylists().get(0).addTrack(moonshineVideo);
        hardCodedUser.getPlaylists().get(2).addTrack(rainVideo);
    }

    /**
     * Returns a randomly generated token and userName
     * if the request contains the correct username and password
     *
     * @param credentials username and password
     * @return Response with either error status or a UserToken and RESOURCE CREATED status
     */
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

    @GET
    @Path("playlists/{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        User foundUser = getUserFromToken(token);

        if (foundUser != null) {
            return Response.status(Response.Status.OK).entity(
                    new AllTracksResponse(hardCodedUser.getPlaylists().get(id).getTracks())
            ).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    User getUserFromToken(String token) {
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
