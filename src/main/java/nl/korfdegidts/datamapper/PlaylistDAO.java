package nl.korfdegidts.datamapper;

import nl.korfdegidts.entity.Playlist;
import nl.korfdegidts.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO extends DataMapper {

    public List<Playlist> getAllPlaylistsFromUser(User user) throws SQLException {

        String token = user.getToken();
        System.out.println(token);

        PreparedStatement stmnt = connection.getConnection().prepareStatement(
                "SELECT playlist_id, name, owner, token " +
                        "FROM playlist p " +
                        "INNER JOIN user u " +
                        "ON p.user_id = u.user_id " +
                        "WHERE u.token = ?");

        stmnt.setString(1, token);

        ResultSet rs = stmnt.executeQuery();

        List<Playlist> playlists = new ArrayList<>();

        while (rs.next()) {
            playlists.add(new Playlist(
                    rs.getInt("playlist_id"),
                    rs.getString("name"),
                    rs.getBoolean("owner"))
            );
        }

        return playlists;
    }
}
