/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Track;

import java.util.List;

public class PlaylistTracksDTO {
    private List<Track> tracks;

    public PlaylistTracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
