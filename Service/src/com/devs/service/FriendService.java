package com.devs.service;


import com.google.gson.JsonObject;
import edu.labIV.entity.Friend;
import edu.labIV.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("friend")
public class FriendService extends Service{

    @GET
    @Path("friendlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFriendList(@PathParam("id") int id){
        List<Friend> friends = getFriendManager().getFriendList(id);
        List<User> userList = new ArrayList<>();
        for(Friend friend : friends){
            userList.add(getUserManager().getUser(friend.getFriendId()));
        }
        return getOkResponse(gson.toJson(userList));
    }

    @GET
    @Path("requestlist/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingList(@PathParam("id") int id){
        List<Friend> request = getFriendManager().getRequestList(id);
        List<User> userList = new ArrayList<>();
        for(Friend friend : request){
            userList.add(getUserManager().getUser(friend.getFriendId()));
        }
        return getOkResponse(gson.toJson(userList));
    }

    @POST
    @Path("add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFriend(String json){
        boolean send = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            int userId = jsonObject.get("userId").getAsInt();
            int friendId = jsonObject.get("friendId").getAsInt();
            Friend sender = new Friend(userId, friendId, "");
            send = getFriendManager().sendFriendRequest(sender);
        }
        catch(Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(send));
    }

    @POST
    @Path("reject")
    @Produces(MediaType.APPLICATION_JSON)
    public Response reject(String json){
        boolean send = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            int userId = jsonObject.get("userId").getAsInt();
            int friendId = jsonObject.get("friendId").getAsInt();
            Friend receiver = new Friend(userId, friendId, "");
            send = getFriendManager().declineRequest(receiver);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(send));
    }

    @POST
    @Path("accept")
    @Produces(MediaType.APPLICATION_JSON)
    public Response accept(String json){
        boolean send = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            int userId = jsonObject.get("userId").getAsInt();
            int friendId = jsonObject.get("friendId").getAsInt();
            Friend receiver = new Friend(userId, friendId, "");
            send = getFriendManager().acceptRequest(receiver);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(send));
    }

    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(String json){
        boolean deleted = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            int userId = jsonObject.get("userId").getAsInt();
            int friendId = jsonObject.get("friendId").getAsInt();
            deleted = getFriendManager().deleteFriend(userId, friendId);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(deleted));
    }




}
