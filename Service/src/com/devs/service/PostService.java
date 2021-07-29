package com.devs.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.labIV.entity.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("post")
public class PostService extends Service {

    @POST
    @Path("new")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response post(String json){
        boolean isPosted = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            String id = jsonObject.get("id").getAsString();
            String text = jsonObject.get("text").getAsString();
            JsonElement photo = jsonObject.get("photo");
            Post post = new Post(Integer.parseInt(id), text, "", LocalDateTime.now());
            isPosted = getPostManager().savePost(post);
            if(isPosted && !photo.isJsonNull() && !photo.getAsString().isEmpty()){
                getPostManager().updatePostPhoto(post, photo.getAsString());
            }
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(isPosted);
    }

    @GET
    @Path("list/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam("id") int id){
        List<Post> list = getPostManager().getAllPost(id);
        return getOkResponse(gson.toJson(list));
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(String json){
        boolean isDeleted = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            int userId = jsonObject.get("userId").getAsInt();
            int postId = jsonObject.get("postId").getAsInt();
            isDeleted = getPostManager().deletePost(userId, postId);

        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(isDeleted);
    }
}
