package com.tyskie.RESTService;

import com.tyskie.DAOs.UserDAOImpl;
import com.tyskie.Domain.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Thijs on 9-6-2017.
 */
@Path("/user")
public class RESTService {
    @Inject
    private UserDAOImpl userDAO;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public String createUser(User user){
        String username = user.getUsername();
        String password = user.getPassword();

        userDAO.createUser(username, password);
        boolean exists = userDAO.checkUser(username, password);

        String users = "User exists: " + exists + " Username: " + username + " Password: " + password;

        return users;
    }
}
