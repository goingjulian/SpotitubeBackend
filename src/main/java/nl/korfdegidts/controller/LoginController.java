package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.UserCredentials;
import nl.korfdegidts.datasource.IDBConnection;
import nl.korfdegidts.datasource.MySQLConnection;
import nl.korfdegidts.datasource.PlaylistDao;
import nl.korfdegidts.dto.TokenDTO;
import nl.korfdegidts.entity.User;
import nl.korfdegidts.exception.UserNotFoundException;
import nl.korfdegidts.service.ILoginService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private ILoginService ILoginService;

    @Inject
    public void setILoginService(nl.korfdegidts.service.ILoginService ILoginService) {
        this.ILoginService = ILoginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials credentials) {
        IDBConnection conn = new MySQLConnection();
        PlaylistDao dao = new PlaylistDao(conn);
        System.out.println(dao.getResult());
        try {
            User foundUser = ILoginService.getUserFromCredentials(credentials);
            return Response.status(Response.Status.CREATED).entity(
                    new TokenDTO(foundUser.getCredentials().getUser(), foundUser.getToken())
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
