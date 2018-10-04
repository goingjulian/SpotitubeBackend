package nl.korfdegidts.dto;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.util.List;

public class PlaylistsDTO {
    private List<Playlist> playlists;
    private int length;

    public PlaylistsDTO(User user) {
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
