/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/7/18 5:20 PM
 */

package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Playlist;

import java.util.List;

public class PlaylistsDTO {
    private List<Playlist> playlists;

    private int length;

    public PlaylistsDTO(List<Playlist> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
