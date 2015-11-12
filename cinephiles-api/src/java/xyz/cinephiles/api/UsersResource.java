/*
 * Work of Charles Waite.
 * Plymouth University.
 */
package xyz.cinephiles.api;

import cinephiles.DAOFactoryDatabase;
import cinephiles.data.DAOFactory;
import cinephiles.data.model.User;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author charliearlie
 */
@Path("/users")
public class UsersResource {
    
    @Context
    private UriInfo context;

    DAOFactory factory;
    
    public UsersResource() {

        factory = new DAOFactoryDatabase();
    }
    
    @GET
    @Produces("application/json")
    public Response getAllUsers() {

        //Check if the factory exists
        if (factory.getConnectionStatus() == true) {
            List<User> memberList = factory.getUserList().getAllUsers();
            Gson gson = new Gson();
            String json = gson.toJson(memberList);
            return Response
                    .ok()
                    .entity(json)
                    .build();
        } else {
            //If the factory does not exists it must have 
            return Response.status(Response.Status.BAD_GATEWAY).entity("Could not connect to database").
                    type(MediaType.TEXT_PLAIN).build();
        }
    }
}
