/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.dto.PlaylistsDTO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.persistence.PlaylistDAO;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {

    private PlaylistDAO dao;
    private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @Inject
    public void setDao(PlaylistDAO dao) {
        this.dao = dao;
    }

    @Override
    public void persistNewPlaylist(Playlist playlist, String username) {
        dao.persistNewPlaylist(playlist, username);
    }

    @Override
    public void deletePlaylist(int playlistId) {
        dao.deletePlaylist(playlistId);
    }

    @Override
    public void editPlaylistName(Playlist playlist) {
        dao.editPlaylist(playlist);
    }

    @Override
    public PlaylistsDTO getAllPlaylistsFromUser(User user) {
        List<Playlist> allPlaylistsFromUser = dao.getAllPlaylistsFromUser(user);
        return new PlaylistsDTO(allPlaylistsFromUser, trackService.getTotalLengthOfAllTracks(user));
    }

}
