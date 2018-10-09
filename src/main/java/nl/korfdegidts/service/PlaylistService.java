package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.PlaylistDAO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import javax.inject.Inject;
import java.util.List;

public class PlaylistService implements IPlaylistService {
    private PlaylistDAO dao = new PlaylistDAO();

    @Inject
    public void setDao(PlaylistDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Playlist> getAllPlaylistsFromUser(User user) {
        return dao.getAllPlaylistsFromUser(user);
    }

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
