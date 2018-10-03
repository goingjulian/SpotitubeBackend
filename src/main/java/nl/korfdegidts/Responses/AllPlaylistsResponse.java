package nl.korfdegidts.Responses;

import nl.korfdegidts.components.Playlist;
import nl.korfdegidts.components.Track;
import nl.korfdegidts.components.User;

import java.util.List;

public class AllPlaylistsResponse {
    private List<Playlist> playlists;
    private int length;

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
