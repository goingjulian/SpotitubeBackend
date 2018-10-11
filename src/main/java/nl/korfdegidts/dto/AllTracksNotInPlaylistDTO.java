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
