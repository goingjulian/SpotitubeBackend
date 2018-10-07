package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.PlaylistDAO;
import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.sql.SQLException;
import java.util.List;

public class PlaylistService implements IPlaylistService {
    private PlaylistDAO dao = new PlaylistDAO();

    @Override
    public List<Playlist> getAllPlaylistsFromUser(User user) {
        try {
            return dao.getAllPlaylistsFromUser(user);
        } catch (SQLException e) {
            System.out.println("Error with SQL");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int calculateTotalLength(List<Playlist> playlists) {
        int total = 0;

//        for (Playlist playlist : playlists) {
//            total += calcualteTotalLengthOfAllTracks(playlist.getTracks());
//        }

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
