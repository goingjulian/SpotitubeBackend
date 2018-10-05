package nl.korfdegidts.service;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;

import java.util.List;

public interface IPlaylistService {
    int calculateTotalLength(List<Playlist> playlists);

    int calcualteTotalLengthOfAllTracks(List<Track> tracks);
}
