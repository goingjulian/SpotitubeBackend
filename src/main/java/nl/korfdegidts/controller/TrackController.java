package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.LoginService;
import nl.korfdegidts.dto.TracksDTO;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class TrackController {
    private LoginService loginService; // = new LoginService();

    @Inject
    public TrackController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GET
    @Path("/{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            return Response.status(Response.Status.OK).entity(
                    new TracksDTO(foundUser.getPlaylists().get(id).getTracks())
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
