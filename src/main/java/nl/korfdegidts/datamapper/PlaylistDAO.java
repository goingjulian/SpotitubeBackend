package nl.korfdegidts.datamapper;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DataMapper {

    private TrackDAO trackDAO;

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public List<Playlist> getAllPlaylistsFromUser(User user) {
        try (
                Connection connection = factory.getDBConnection().getConnection();

                PreparedStatement stmnt = connection.prepareStatement(
                        "SELECT playlist_id, name, owner " +
                                "FROM playlist p " +
                                "INNER JOIN user u " +
                                "ON p.user_id = u.user_id " +
                                "INNER JOIN token t " +
                                "ON t.username = u.username " +
                                "WHERE u.username = ? ")
        ) {
            stmnt.setString(1, user.getCredentials().getUser());

            ResultSet rs = stmnt.executeQuery();

            List<Playlist> playlists = new ArrayList<>();

            while (rs.next()) {
                Playlist playlist = mapResult(rs);
                playlist.setTracks(trackDAO.getAllTracksInPlaylist(playlist.getId()));

                playlists.add(playlist);
            }

            return playlists;
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
