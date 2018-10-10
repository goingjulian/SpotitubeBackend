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

            List<Track> tracks = new ArrayList<>();
            while (rs.next()) {

                tracks.add(mapResult(rs));
            }

            return tracks;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
