package com.devs.service;

import com.google.gson.JsonObject;
import edu.labIV.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Path("user")
public class UserService extends Service{

    @POST
    @Path("list")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserList(String idString){
        List<User> userList = new ArrayList<>();
        try{
            int id = Integer.parseInt(idString);
            userList = manager.getAddableUserList(id);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(userList));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id){
        User user = getUserManager().getUser(id);
        return getOkResponse(gson.toJson(user));
    }

    @POST
    @Path("edit")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(String string){
        User user = new User();
        try{
            JsonObject json = gson.fromJson(string, JsonObject.class);
            int id = json.get("id").getAsInt();
            user = manager.getUserManager().getUserNoPhoto(id);
            user.setName(json.get("name").getAsString());
            user.setLastname(json.get("lastname").getAsString());
            user.setBirthdate(gson.fromJson(json.get("birthdate"), LocalDate.class));
            getUserManager().updateUser(user);
            user = getUserManager().getUser(id);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(user));
    }
}
