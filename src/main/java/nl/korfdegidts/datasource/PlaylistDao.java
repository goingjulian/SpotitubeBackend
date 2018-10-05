package nl.korfdegidts.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaylistDao {
    private IDBConnection conn;
    private String result;

    public PlaylistDao(IDBConnection conn) {
        this.conn = conn;

        try {
            Statement st = conn.getConnection().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user ");
            rs.next();
            result = rs.getString("username");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return result;
    }
}
