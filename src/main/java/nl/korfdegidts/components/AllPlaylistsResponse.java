package nl.korfdegidts.components;

import java.util.List;

public class AllPlaylistsResponse {
    private List<Playlist> playlists;
    int length = 0;

    public AllPlaylistsResponse(User user) {
        playlists = user.getPlaylists();
        length = calculateTotalLenght();
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

    public int getLength() {
        return length;
    }
}
