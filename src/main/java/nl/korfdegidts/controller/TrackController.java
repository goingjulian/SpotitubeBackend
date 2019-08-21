/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 11/1/18 11:54 AM
 */

package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.Secure;
import nl.korfdegidts.service.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private ITrackService trackService;

    @Inject
    public void setTrackService(ITrackService trackService) {
        this.trackService = trackService;
    }

    @Secure
    @GET
    public Response getAllTracksNotInPlaylist(@QueryParam("forPlaylist") int playlistId) {
        return Response.status(Response.Status.OK).entity(
                trackService.getAllTracksNotInPlaylist(playlistId)
        ).build();
    }


}
