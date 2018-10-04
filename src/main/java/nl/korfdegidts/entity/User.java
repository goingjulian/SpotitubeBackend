package nl.korfdegidts.entity;

import nl.korfdegidts.authentication.LoginCredentials;
import nl.korfdegidts.authentication.UserToken;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Playlist> playlists = new ArrayList<>();

    private LoginCredentials credentials;
    private UserToken token;

    public User(LoginCredentials credentials) {
        this.credentials = credentials;
        token = new UserToken(credentials.getUser(), generateToken());
    }

    public LoginCredentials getCredentials() {
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

    public void addPlaylist(int id, String name) {
        playlists.add(new Playlist(id, name, true));
    }
}
