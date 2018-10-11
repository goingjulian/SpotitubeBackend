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
