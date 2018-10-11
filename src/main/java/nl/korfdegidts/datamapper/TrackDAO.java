package nl.korfdegidts.datamapper;

import nl.korfdegidts.entity.Track;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DataMapper {

    public List<Track> getAllTracksInPlaylist(int playlistId) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT track_id, title, performer, duration, playcount, offlineAvailable, album, description, publication_date " +
                                "FROM track t " +
                                "INNER JOIN playlist p " +
                                "ON t.playlist_id = p.playlist_id " +
                                "WHERE t.playlist_id = ? "
                )
        ) {
            stmnt.setInt(1, playlistId);

            ResultSet rs = stmnt.executeQuery();

            return bindResultSetToTrackList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getTotalLengthOfAllTracks(String username) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT SUM(duration) AS total from track t " +
                                "inner join playlist p " +
                                "on t.playlist_id = p.playlist_id " +
                                "INNER JOIN user u " +
                                "ON u.username = p.username " +
                                "WHERE u.username = ? "
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
                                "FROM track t WHERE NOT playlist_id = ?"
                )
        ) {
            stmnt.setInt(1, playlistId);

            ResultSet rs = stmnt.executeQuery();

            return bindResultSetToTrackList(rs);

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
