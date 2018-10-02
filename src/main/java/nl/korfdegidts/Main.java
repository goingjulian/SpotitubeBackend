package nl.korfdegidts;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")
public class Main {

    @GET
    public String live() {
        return "Hello world";
    }
}
