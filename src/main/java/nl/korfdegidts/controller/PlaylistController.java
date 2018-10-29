/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.controller;

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

            return Response.status(Response.Status.OK).entity(
                    playlistService.getAllPlaylistsFromUser(foundUser)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistNewPlaylist(@QueryParam("token") String token, Playlist playlist) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            playlistService.persistNewPlaylist(playlist, foundUser.getCredentials().getUser());

            return Response.status(Response.Status.OK).entity(
                    playlistService.getAllPlaylistsFromUser(foundUser)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            playlistService.deletePlaylist(playlistId);

            return Response.status(Response.Status.OK).entity(
                    playlistService.getAllPlaylistsFromUser(foundUser)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
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

            return Response.status(Response.Status.OK).entity(
                    playlistService.getAllPlaylistsFromUser(foundUser)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            return Response.status(Response.Status.OK).entity(
                    trackService.getAllTracksInPlaylistDTO(playlistId)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@QueryParam("token") String token,
                                       @PathParam("id") int playlistId,
                                       Track track) {
        try {
            User foundUser = loginService.getUserFromToken(token);
            trackService.addTrackToPlaylist(playlistId, track);
            return Response.status(Response.Status.OK).entity(
                    trackService.getAllTracksInPlaylistDTO(playlistId)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
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
                    trackService.getAllTracksInPlaylistDTO(playlistId)
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
