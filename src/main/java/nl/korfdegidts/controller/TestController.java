package nl.korfdegidts.controller;

import nl.korfdegidts.datasource.IDBConnection;
import nl.korfdegidts.datasource.MySQLConnection;
import nl.korfdegidts.datasource.PlaylistDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TestController {
    IDBConnection conn = new MySQLConnection();
    PlaylistDao dao = new PlaylistDao(conn);

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        System.out.println(dao.getResult());
        return dao.getResult();
    }

}
