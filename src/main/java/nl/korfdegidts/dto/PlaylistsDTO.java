package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import java.util.List;

public class PlaylistsDTO {
    private List<Playlist> playlists;

    private int length;

    public PlaylistsDTO(User user, int length) {
        playlists = user.getPlaylists();
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
