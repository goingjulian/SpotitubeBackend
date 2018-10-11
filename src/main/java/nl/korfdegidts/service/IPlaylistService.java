/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 12:10 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface IPlaylistService {

    List<Playlist> getAllPlaylistsFromUser(User user);

    void persistNewPlaylist(Playlist playlist, String username);

    void deletePlaylist(int playlistId);

    void editPlaylistName(Playlist playlist);
}
