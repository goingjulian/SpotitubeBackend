package nl.korfdegidts.service;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import java.util.List;

public interface IPlaylistService {

    List<Playlist> getAllPlaylistsFromUser(User user);

    void persistNewPlaylist(Playlist playlist, String username);

    void deletePlaylist(int playlistId);

    void editPlaylistName(Playlist playlist);
}
