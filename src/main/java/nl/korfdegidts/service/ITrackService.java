package nl.korfdegidts.service;

import nl.korfdegidts.entity.Track;

import java.util.List;

public interface ITrackService {
    List<Track> getTracksFromPlaylist(int playlistId);
}
