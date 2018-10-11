package nl.korfdegidts.service;

import nl.korfdegidts.datamapper.TrackDAO;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

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

    @Override
    public List<Track> getAllTracks(int playlistId) {
        return dao.getAllTracks(playlistId);
    }

    @Override
    public int getTotalLengthOfAllTracks(User user) {
        return dao.getTotalLengthOfAllTracksInPlaylist(user.getCredentials().getUser());
    }

    @Override
    public void addTrackToPlaylist(int playlistId, Track track) {
        dao.addTrackToPlaylist(playlistId, track);
    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        dao.deleteTrackFromPlaylist(playlistId, trackId);
    }
}
