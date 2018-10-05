package nl.korfdegidts.service;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;

import java.util.List;

public class PlaylistService implements IPlaylistService {

    @Override
    public int calculateTotalLength(List<Playlist> playlists) {
        int total = 0;

        for (Playlist playlist : playlists) {
            total += calcualteTotalLengthOfAllTracks(playlist.getTracks());
        }

        return total;
    }

    @Override
    public int calcualteTotalLengthOfAllTracks(List<Track> tracks) {
        int total = 0;

        for (Track track : tracks) {
            total += track.getDuration();
        }

        return total;
    }
}
