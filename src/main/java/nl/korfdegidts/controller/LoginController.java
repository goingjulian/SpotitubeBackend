package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.LoginService;
import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private LoginService loginService = new LoginService();

//    @Inject
//    public LoginController(LoginService loginService) {
//        this.loginService = loginService;
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials credentials) {
        try {
            User foundUser = loginService.loginUser(credentials);
            return Response.status(Response.Status.CREATED).entity(
                    foundUser.getTokenObject()
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

//    @GET
//    @Path("playlists")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getAllPlaylistsFromUser(@QueryParam("token") String token) {
//        try {
//            User foundUser = loginService.getUserFromToken(token);
//            return Response.status(Response.Status.OK).entity(
//                    new PlaylistsDTO(foundUser)
//            ).build();
//        } catch (UserNotFoundException e) {
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//    }

//    @GET
//    @Path("playlists/{id}/tracks")
//    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
//        try {
//            User foundUser = loginService.getUserFromToken(token);
//            return Response.status(Response.Status.OK).entity(
//                    new TracksDTO(foundUser.getPlaylists().get(id).getTracks())
//            ).build();
//        } catch (UserNotFoundException e) {
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
//    }


}
