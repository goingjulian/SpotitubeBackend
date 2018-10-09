package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.TrackDAO;
import nl.korfdegidts.entity.Track;

import javax.inject.Inject;
import java.util.List;

public class TrackService implements ITrackService {
    private TrackDAO dao = new TrackDAO();

    @Inject
    public void setDao(TrackDAO dao) {
        this.dao = dao;
    }

    @Override
    public List<Track> getTracksFromPlaylist(int playlistId) {
        return dao.getAllTracksInPlaylist(playlistId);
    }
}
