/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 9:52 PM
 */

package nl.korfdegidts.controller;

import nl.korfdegidts.dto.PlaylistTracksDTO;
import nl.korfdegidts.dto.PlaylistsDTO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
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
    public Response getTracksFromPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            return Response.status(Response.Status.OK).entity(
                    new PlaylistTracksDTO(trackService.getTracksFromPlaylist(playlistId))
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private PlaylistsDTO getAllPlaylistsFromUserHelper(User user) {
        List<Playlist> playlists = playlistService.getAllPlaylistsFromUser(user);
        return new PlaylistsDTO(playlists, trackService.getTotalLengthOfAllTracks(user));
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, Track track) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            trackService.addTrackToPlaylist(playlistId, track);
            return Response.status(Response.Status.OK).entity(
                    new PlaylistTracksDTO(trackService.getTracksFromPlaylist(playlistId))
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token,
                                            @PathParam("playlistId") int playlistId,
                                            @PathParam("trackId") int trackId) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            trackService.deleteTrackFromPlaylist(playlistId, trackId);
            return Response.status(Response.Status.OK).entity(
                    new PlaylistTracksDTO(trackService.getAllTracks(playlistId))
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
