package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.TrackDAO;
import nl.korfdegidts.entity.Track;

import java.sql.SQLException;
import java.util.List;

public class TrackService implements ITrackService {
    private TrackDAO dao = new TrackDAO();

    @Override
    public List<Track> getTracksFromPlaylist(int playlistId) {
        try {
            return dao.getAllTracksInPlaylist(playlistId);
        } catch (SQLException e) {
            System.out.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }
}
