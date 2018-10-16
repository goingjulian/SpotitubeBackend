/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/16/18 3:33 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.dto.PlaylistTracksDTO;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

public interface ITrackService {
    PlaylistTracksDTO getAllTracksInPlaylistDTO(int playlistId);

    PlaylistTracksDTO getAllTracksNotInPlaylist(int playlistId);

    int getTotalLengthOfAllTracks(User user);

    void addTrackToPlaylist(int playlistId, Track track);

    void deleteTrackFromPlaylist(int playlistId, int trackId);
}
