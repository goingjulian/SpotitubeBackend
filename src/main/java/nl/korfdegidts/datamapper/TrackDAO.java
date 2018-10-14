/*
 * Copyright (c) 2018. Proprietary and confidential.
 * Developed by Julian Korf de Gidts.
 *
 * All rights reserved. Unauthorized copying, reverse engineering, transmission, public performance or rental of this software is strictly prohibited.
 *
 * File last modified: 10/11/18 9:52 PM
 */

package nl.korfdegidts.datamapper;

import nl.korfdegidts.entity.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DataMapper {//<> generic

    public List<Track> getAllTracksInPlaylist(int playlistId) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT t.track_id, title, performer, duration, playcount, p.offlineAvailable, album, description, publication_date " +
                                "FROM track t " +
                                "INNER JOIN trackPlaylist p " +
                                "ON t.track_id = p.track_id " +
                                "WHERE p.playlist_id = ? "
                )
        ) {
            stmnt.setInt(1, playlistId);

            ResultSet rs = stmnt.executeQuery();

            return bindResultSetToTrackList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalLengthOfAllTracksInPlaylist(String username) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT SUM(duration) AS total FROM track t\n" +
                                "INNER JOIN trackPlaylist p " +
                                "ON t.track_id = p.track_id " +
                                "INNER JOIN playlist y " +
                                "ON y.playlist_id = p.playlist_id " +
                                "INNER JOIN user u " +
                                "ON u.username = y.username " +
                                "WHERE u.username = ?"
                )
        ) {
            stmnt.setString(1, username);

            ResultSet rs = stmnt.executeQuery();

            rs.next();
            return rs.getInt("total");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Track> getAllTracks(int playlistId) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT track_id, title, performer, duration, playcount, offlineAvailable, album, description, publication_date " +
                                "FROM track t " +
                                "WHERE t.track_id NOT IN " +
                                "(SELECT track_id " +
                                "FROM trackPlaylist p " +
                                "INNER JOIN playlist s " +
                                "ON s.playlist_id = p.playlist_id " +
                                "WHERE p.playlist_id = ?)"
                )
        ) {
            stmnt.setInt(1, playlistId);

            ResultSet rs = stmnt.executeQuery();

            return bindResultSetToTrackList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addTrackToPlaylist(int playlistId, Track track) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "INSERT INTO trackPlaylist (track_id, playlist_id, offlineAvailable) VALUES (?, ?, ?)"

                )
        ) {
            stmnt.setInt(1, track.getId());
            stmnt.setInt(2, playlistId);
            stmnt.setBoolean(3, track.isOfflineAvailable());

            stmnt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "DELETE FROM trackPlaylist WHERE track_id = ? AND playlist_id = ?"
                )
        ) {
            stmnt.setInt(1, trackId);
            stmnt.setInt(2, playlistId);

            stmnt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Track> bindResultSetToTrackList(ResultSet rs) throws SQLException {
        List<Track> tracks = new ArrayList<>();
        while (rs.next()) {

            tracks.add(mapResult(rs));
        }
        return tracks;
    }

    @Override
    protected Track mapResult(ResultSet rs) throws SQLException {
        return new Track(
                rs.getInt("track_id"),
                rs.getString("title"),
                rs.getString("performer"),
                rs.getInt("duration"),
                rs.getInt("playcount"),
                rs.getBoolean("offlineAvailable"),
                rs.getString("album"),
                rs.getString("description"),
                rs.getString("publication_date")
        );
    }
}
