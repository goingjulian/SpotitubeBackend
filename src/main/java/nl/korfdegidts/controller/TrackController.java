package nl.korfdegidts.controller;

import nl.korfdegidts.dto.AllTracksNotInPlaylistDTO;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;
import nl.korfdegidts.service.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TrackController {

    private ILoginService loginService;
    private ITrackService trackService;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            List<Track> tracks = trackService.getAllTracks(playlistId);
            return Response.status(Response.Status.OK).entity(
                    new AllTracksNotInPlaylistDTO(tracks)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.OK).build();
        }
    }


}
