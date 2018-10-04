package nl.korfdegidts.entity;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.authentication.UserToken;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Playlist> playlists = new ArrayList<>();

    private UserCredentials credentials;
    private UserToken token;

    public User(UserCredentials credentials) {
        this.credentials = credentials;
        token = new UserToken(credentials.getUser(), generateToken());
    }

    public UserCredentials getCredentials() {
        return credentials;
    }

    public UserToken getTokenObject() {
        return token;
    }

    /**
     * Replace with DB!!!
     */
    private String generateToken() {
        return "1234-1234-1234";
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(int id, String name, boolean isOwner) {
        playlists.add(new Playlist(id, name, isOwner));
    }
}
