package nl.korfdegidts.controller;

import nl.korfdegidts.dto.PlaylistsDTO;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    @Inject
    private ILoginService ILoginService;

//    @Inject
//    public PlaylistController(LoginServiceHardCoded loginService) {
//        this.loginService = loginService;
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylistsFromUser(@QueryParam("token") String token) {
        try {
            User foundUser = ILoginService.getUserFromToken(token);
            return Response.status(Response.Status.OK).entity(
                    new PlaylistsDTO(foundUser)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
