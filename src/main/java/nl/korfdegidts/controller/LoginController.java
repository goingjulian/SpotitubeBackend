package nl.korfdegidts.controller;

import nl.korfdegidts.authentication.UserCredentials;
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

    private ILoginService loginService;

    @Inject
    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials credentials) {
        try {
//            User foundUser = loginService.getUserFromCredentials(credentials);
            return Response.status(Response.Status.CREATED).entity(
                    loginService.getTokenDTOFromCredentials(credentials)
//                    new TokenDTO(foundUser.getCredentials().getUser(), "1234-1234-1234")
            ).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }


}
