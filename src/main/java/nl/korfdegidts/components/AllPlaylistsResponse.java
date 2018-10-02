package nl.korfdegidts.components;

import java.util.List;

public class AllPlaylistsResponse {
    int totalLength = 0;
    private List<Playlist> playlists;

    public AllPlaylistsResponse(User user) {
        playlists = user.getPlaylists();
        totalLength = calculateTotalLenght();
    }

    private int calculateTotalLenght() {
        int total = 0;

        for (Playlist playlist : playlists) {
            total += calculateLengthOfAllTracks(playlist.getTracks());
        }

        return total;
    }

    private int calculateLengthOfAllTracks(List<Track> tracks) {
        int total = 0;

        for (Track track : tracks) {
            total += track.getDuration();
        }

        return total;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public int getTotalLength() {
        return totalLength;
    }
}
