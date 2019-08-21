/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 11/1/18 11:54 AM
 */

package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.AuthenticatedUser;
import nl.korfdegidts.authentication.Role;
import nl.korfdegidts.authentication.Secure;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.service.IPlaylistService;
import nl.korfdegidts.service.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private IPlaylistService playlistService;
    private ITrackService trackService;

    @Inject
    public void setPlaylistService(IPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @Secure
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPlaylistsFromUser() {
        User user = AuthenticatedUser.getAuthenticatedUser();

        return Response.status(Response.Status.OK).entity(
                playlistService.getAllPlaylistsFromUser(user)
        ).build();
    }

    @Secure(allowedRoles = {Role.ADMIN})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response persistNewPlaylist(Playlist playlist) {
        User user = AuthenticatedUser.getAuthenticatedUser();

        playlistService.persistNewPlaylist(playlist, user.getCredentials().getUser());

        return Response.status(Response.Status.OK).entity(
                playlistService.getAllPlaylistsFromUser(user)
        ).build();
    }

    @Secure(allowedRoles = {Role.ADMIN})
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistId) {
        User user = AuthenticatedUser.getAuthenticatedUser();
        playlistService.deletePlaylist(playlistId);

        return Response.status(Response.Status.OK).entity(
                playlistService.getAllPlaylistsFromUser(user)
        ).build();
    }

    @Secure(allowedRoles = {Role.ADMIN})
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylistName(Playlist playlist) {
        User user = AuthenticatedUser.getAuthenticatedUser();

        playlistService.editPlaylistName(playlist);

        return Response.status(Response.Status.OK).entity(
                playlistService.getAllPlaylistsFromUser(user)
        ).build();
    }

    @Secure
    @GET
    @Path("/{id}/tracks")
    public Response getTracksFromPlaylist(@PathParam("id") int playlistId) {
        return Response.status(Response.Status.OK).entity(
                trackService.getAllTracksInPlaylistDTO(playlistId)
        ).build();
    }

    @Secure(allowedRoles = {Role.ADMIN})
    @POST
    @Path("/{id}/tracks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(@PathParam("id") int playlistId, Track track) {
        trackService.addTrackToPlaylist(playlistId, track);
        return Response.status(Response.Status.OK).entity(
                trackService.getAllTracksInPlaylistDTO(playlistId)
        ).build();
    }

    @Secure(allowedRoles = {Role.ADMIN})
    @DELETE
    @Path("/{playlistId}/tracks/{trackId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId,
                                            @PathParam("trackId") int trackId) {
        trackService.deleteTrackFromPlaylist(playlistId, trackId);
        return Response.status(Response.Status.OK).entity(
                trackService.getAllTracksInPlaylistDTO(playlistId)
        ).build();
    }
}
