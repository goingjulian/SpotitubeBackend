/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/29/18 11:22 AM
 */

package nl.korfdegidts.entity;

import java.util.ArrayList;
import java.util.List;

public class User implements IEntity {
    private List<Playlist> playlists = new ArrayList<>(); //REMOVE

    private UserCredentials credentials;
    private String token;

    public User(UserCredentials credentials) {
        this.credentials = credentials;
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public String getToken() {
        return token;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(int id, String name, boolean isOwner) {
        playlists.add(new Playlist(id, name, isOwner));
    }

    public void setToken(String token) {
        this.token = token;
    }
}
