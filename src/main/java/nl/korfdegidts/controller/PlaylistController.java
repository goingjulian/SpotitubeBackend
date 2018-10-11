package nl.korfdegidts.controller;

import nl.korfdegidts.dto.PlaylistsDTO;
import nl.korfdegidts.dto.TracksDTO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;
import nl.korfdegidts.service.IPlaylistService;
import nl.korfdegidts.service.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/playlists")
public class PlaylistController {

    private ILoginService loginService;
    private IPlaylistService playlistService;
    private ITrackService trackService;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylistsFromUser(@QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);

            PlaylistsDTO dto = getAllPlaylistsFromUserHelper(foundUser);

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistNewPlaylist(@QueryParam("token") String token, Playlist playlist) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            playlistService.persistNewPlaylist(playlist, foundUser.getCredentials().getUser());

            PlaylistsDTO dto = getAllPlaylistsFromUserHelper(foundUser);

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            playlistService.deletePlaylist(playlistId);

            PlaylistsDTO dto = getAllPlaylistsFromUserHelper(foundUser);

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(@QueryParam("token") String token, Playlist playlist) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            playlistService.editPlaylistName(playlist);

            PlaylistsDTO dto = getAllPlaylistsFromUserHelper(foundUser);

            return Response.status(Response.Status.OK).entity(dto).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            return Response.status(Response.Status.OK).entity(
                    new TracksDTO(trackService.getTracksFromPlaylist(id))
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private PlaylistsDTO getAllPlaylistsFromUserHelper(User user) {
        List<Playlist> playlists = playlistService.getAllPlaylistsFromUser(user);
        return new PlaylistsDTO(playlists, trackService.getTotalLengthOfAllTracks(user));
    }
}
