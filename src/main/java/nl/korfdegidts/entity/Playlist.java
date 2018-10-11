/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 12:11 PM
 */

package nl.korfdegidts.entity;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements IEntity {
    private int id;

    private String name;

    private boolean owner;

    private List<Track> tracks = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(int id, String name, boolean owner) {
        this.name = name;
        this.owner = owner;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }
}
