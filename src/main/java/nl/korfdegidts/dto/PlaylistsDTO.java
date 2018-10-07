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
