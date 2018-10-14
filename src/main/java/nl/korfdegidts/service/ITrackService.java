/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/14/18 3:28 PM
 */

package nl.korfdegidts.service;

import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface ITrackService {
    List<Track> getTracksFromPlaylist(int playlistId);

    List<Track> getAllTracks(int playlistId);

    int getTotalLengthOfAllTracks(User user);

    void addTrackToPlaylist(int playlistId, Track track);

    void deleteTrackFromPlaylist(int playlistId, int trackId);
}
