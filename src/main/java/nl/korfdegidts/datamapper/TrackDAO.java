package nl.korfdegidts.datamapper;

import nl.korfdegidts.entity.Track;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DataMapper {

    public List<Track> getAllTracksInPlaylist(int playlistId) throws SQLException {
        PreparedStatement stmnt = connection.getConnection().prepareStatement(
                "SELECT track_id, title, performer, duration, playcount, offlineAvailable, album, description, publication_date " +
                        "FROM track t " +
                        "INNER JOIN playlist p " +
                        "ON t.playlist_id = p.playlist_id " +
                        "WHERE t.playlist_id = ? "
        );

        stmnt.setInt(1, playlistId);

        ResultSet rs = stmnt.executeQuery();

        List<Track> tracks = new ArrayList<>();
        while (rs.next()) {

            tracks.add(new Track(
                    rs.getInt("track_id"),
                    rs.getString("title"),
                    rs.getString("performer"),
                    rs.getInt("duration"),
                    rs.getInt("playcount"),
                    rs.getBoolean("offlineAvailable"),
                    rs.getString("album"),
                    rs.getString("description"),
                    "02-02-1992"
            ));
        }

        return tracks;
    }
}
