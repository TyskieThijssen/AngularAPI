package com.tyskie.RESTService;

import com.tyskie.DAOs.UserDAOImpl;
import com.tyskie.Domain.User;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/show/all")
    public Response showAllUsers(){
        List<User> users = userDAO.getAllUsers();

        JSONObject userObject = new JSONObject();
        JSONArray userArray = new JSONArray();

        for (User user : users) {
            JSONObject userInformation = new JSONObject();
            String username = user.getUsername();
            String password = user.getPassword();
            userInformation.put("username", username);
            userInformation.put("password", password);
            userArray.add(userInformation);
        }

        userObject.put("users", userArray);

        return Response.status(200).entity(userObject).build();
    }
}
