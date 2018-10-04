package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Track;

import java.util.List;

public class TracksDTO {
    private List<Track> tracks;

    public TracksDTO(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
