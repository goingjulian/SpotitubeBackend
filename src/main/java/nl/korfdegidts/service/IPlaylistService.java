package nl.korfdegidts.service;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.Track;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface IPlaylistService {

    List<Playlist> getAllPlaylistsFromUser(User user);

    int calculateTotalLength(List<Playlist> playlists);

    int calcualteTotalLengthOfAllTracks(List<Track> tracks);
}
