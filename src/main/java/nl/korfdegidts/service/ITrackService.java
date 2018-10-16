/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/16/18 2:36 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.dto.PlaylistTracksDTO;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface ITrackService {
    PlaylistTracksDTO getPlaylistsTracksDTOFromPlaylist(int playlistId);

    List<Track> getAllTracks(int playlistId);

    int getTotalLengthOfAllTracks(User user);

    void addTrackToPlaylist(int playlistId, Track track);

    void deleteTrackFromPlaylist(int playlistId, int trackId);
}
