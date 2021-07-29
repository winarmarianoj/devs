package com.devs.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.labIV.logger.Logger;
import edu.labIV.manager.*;

import javax.ws.rs.core.Response;

public abstract class Service {

    protected BackEndManager manager;
    protected Gson gson;

    public Service() {
        manager = new BackEndManager();
        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    }

    public Response getOkResponse(){
        return Response.ok().build();
    }

    public Response getOkResponse(Object object){
        return Response.ok().entity(object).build();
    }

    public AccountManager getAccountManager(){
        return manager.getAccountManager();
    }

    public FriendManager getFriendManager(){
        return manager.getFriendManager();
    }

    public PostManager getPostManager(){
        return manager.getPostManager();
    }

    public UserManager getUserManager(){
        return manager.getUserManager();
    }

    public void logUknownError(String message){
        Logger.getInstance().logError(message);
    }
}
