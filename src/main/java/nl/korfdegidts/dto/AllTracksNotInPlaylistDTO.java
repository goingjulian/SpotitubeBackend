/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/14/18 3:28 PM
 */

package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Track;

import java.util.List;

public class AllTracksNotInPlaylistDTO {
    private List<Track> tracks;

    public AllTracksNotInPlaylistDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
