/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/23/18 4:25 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.dto.PlaylistTracksDTO;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.persistence.TrackDAO;

import javax.inject.Inject;

public class TrackService implements ITrackService {
    private TrackDAO dao;

    @Inject
    public void setDao(TrackDAO dao) {
        this.dao = dao;
    }

    @Override
    public PlaylistTracksDTO getAllTracksInPlaylistDTO(int playlistId) {
        return new PlaylistTracksDTO(dao.getAllTracksInPlaylist(playlistId));
    }

    @Override
    public PlaylistTracksDTO getAllTracksNotInPlaylist(int playlistId) {
        return new PlaylistTracksDTO(dao.getAllTracksNotInPlaylist(playlistId));
    }

    @Override
    public int getTotalLengthOfAllTracks(User user) {
        return dao.getTotalLengthOfAllTracksInPlaylist(user.getCredentials().getUser());
    }

    @Override
    public void addTrackToPlaylist(int playlistId, Track track) {
        dao.addTrackToPlaylist(playlistId, track);
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        dao.deleteTrackFromPlaylist(playlistId, trackId);
    }
}
