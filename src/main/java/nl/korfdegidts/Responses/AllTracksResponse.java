package nl.korfdegidts.Responses;

import nl.korfdegidts.components.Track;

import java.util.List;

public class AllTracksResponse {
    private List<Track> tracks;

    public AllTracksResponse(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
