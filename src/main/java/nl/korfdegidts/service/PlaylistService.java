/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 12:10 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.PlaylistDAO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {
    private PlaylistDAO dao = new PlaylistDAO();

    @Inject
    public void setDao(PlaylistDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Playlist> getAllPlaylistsFromUser(User user) {
        return dao.getAllPlaylistsFromUser(user);
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


}
