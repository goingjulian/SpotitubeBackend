package nl.korfdegidts.controller;

import nl.korfdegidts.dto.PlaylistsDTO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;
import nl.korfdegidts.service.IPlaylistService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController {

    private ILoginService loginService;
    private IPlaylistService playlistService;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylistsFromUser(@QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            List<Playlist> playlists = playlistService.getAllPlaylistsFromUser(foundUser);
            return Response.status(Response.Status.OK).entity(
                    new PlaylistsDTO(playlists, playlistService.calculateTotalLength(playlists))
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
