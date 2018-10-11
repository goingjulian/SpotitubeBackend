package nl.korfdegidts.service;

import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface ITrackService {
    List<Track> getTracksFromPlaylist(int playlistId);

    List<Track> getAllTracks(int playlistId);

    int getTotalLengthOfAllTracks(User user);
}
