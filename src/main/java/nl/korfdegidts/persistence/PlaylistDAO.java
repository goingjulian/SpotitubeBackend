/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/23/18 4:08 PM
 */

package nl.korfdegidts.persistence;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DAO {

    public List<Playlist> getAllPlaylistsFromUser(User user) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT playlist_id, name, owner " +
                                "FROM playlist p " +
                                "INNER JOIN user u " +
                                "ON p.username = u.username " +
                                "WHERE u.username = ? ")
        ) {
            stmnt.setString(1, user.getCredentials().getUser());

            ResultSet rs = stmnt.executeQuery();

            List<Playlist> playlists = new ArrayList<>();

            while (rs.next()) {
                Playlist playlist = mapResult(rs);

                playlists.add(playlist);
            }

            return playlists;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void persistNewPlaylist(Playlist playlist, String username) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "INSERT INTO playlist (name, owner, username) " +
                                "VALUES (?, ?, ?)")
        ) {
            stmnt.setString(1, playlist.getName());
            stmnt.setBoolean(2, true);
            stmnt.setString(3, username);

            stmnt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePlaylist(int playlistId) {
        try (
                Connection connection = factory.getDBConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement("DELETE FROM playlist WHERE playlist_id = ?")
        ) {
            stmnt.setInt(1, playlistId);
            stmnt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPlaylist(Playlist playlist) {
        try (
                Connection connection = factory.getDBConnection().getConnection();
                PreparedStatement stmnt = connection.prepareStatement("UPDATE playlist SET name = ? WHERE playlist_id = ?")
        ) {
            stmnt.setString(1, playlist.getName());
            stmnt.setInt(2, playlist.getId());
            stmnt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Playlist mapResult(ResultSet rs) throws SQLException {
        return new Playlist(
                rs.getInt("playlist_id"),
                rs.getString("name"),
                rs.getBoolean("owner")
        );
    }
}
