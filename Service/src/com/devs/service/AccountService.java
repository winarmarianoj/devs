package com.devs.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.labIV.entity.Account;
import edu.labIV.entity.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Path("account")
public class AccountService extends Service {

    private static  final String ACTIVATION_SUCCESS_HTML =
            "<html>\n" +
            "<head>\n" +
            "<title>Redirigiendo...</title>\n" +
            "<META HTTP-EQUIV=\"REFRESH\" CONTENT=\"5;URL=http://localhost:8080/Devs/login.html\">\n" +
            "</head>\n" +
            "\t<img src=\"http://localhost:8080/Devs/img/logos.png\" width=\"10%\" style=\"margin:0% 3.8%\">\n" +
            "\t<p style=\"font-size:130%\">Su cuenta ha sido activada exitosamente!</p>\n" +
            "</html> ";

    private static  final String ACTIVATION_FAILED_HTML =
            "<html>\n" +
                    "<head>\n" +
                    "<title>Redirigiendo...</title>\n" +
                    "<META HTTP-EQUIV=\"REFRESH\" CONTENT=\"5;URL=http://localhost:8080/Devs/login.html\">\n" +
                    "</head>\n" +
                    "\t<img src=\"http://localhost:8080/Devs/img/logos.png\" width=\"10%\" style=\"margin:0% 3.8%\">\n" +
                    "\t<p style=\"font-size:130%\">Fallo la activacion de la cuenta</p>\n" +
                    "</html> ";

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccount(@PathParam("email") String email){
        Account account = getAccountManager().getAccount(email);
        return getOkResponse(gson.toJson(account));
    }

    @POST
    @Path("login")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String json){
        User user = null;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            String email = jsonObject.get("email").getAsString().toLowerCase();
            String password = jsonObject.get("password").getAsString();
            boolean login = manager.logIn(email, password);
            if(login) {
                user = getUserManager().getUser(getAccountManager().getAccount(email).getId());
            }
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(gson.toJson(user));
    }

    @POST
    @Path("register")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response register(String json){
        boolean isSignedIn = false;
        try {
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            String email = jsonObject.get("email").getAsString().toLowerCase();
            String password = jsonObject.get("password").getAsString();
            String name = jsonObject.get("name").getAsString();
            String lastName = jsonObject.get("lastName").getAsString();
            int year = jsonObject.get("year").getAsInt();
            int month = jsonObject.get("month").getAsInt();
            int day = jsonObject.get("day").getAsInt();
            User user = new User(name, lastName, LocalDate.of(year, month, day));
            JsonElement photo = jsonObject.get("photo");
            if (isSignedIn = manager.signIn(email, password, user)) {
                if (!photo.isJsonNull() && !photo.getAsString().isEmpty()) {
                    int userId = getAccountManager().getAccount(email).getId();
                    user.setId(userId);
                    getUserManager().updatePhoto(user, photo.getAsString());
                }
            }
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }
        return getOkResponse(isSignedIn);
    }

    @GET
    @Path("activate/{id}")
    @Produces(MediaType.TEXT_HTML)
    public Response activate(@PathParam("id") int id) {
        String html = ACTIVATION_FAILED_HTML;
        if(manager.activate(id)){
            html = ACTIVATION_SUCCESS_HTML;
        }
        return getOkResponse(html);
    }

    @POST
    @Path("forgot")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response forgot(String json){
        boolean hasChanged = false;
        try{
            JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
            String email = jsonObject.get("email").getAsString().toLowerCase();
            String newPassword = jsonObject.get("password").getAsString();
            hasChanged = getAccountManager().changePassword(email, newPassword);
        }catch (Exception ex){
            logUknownError(ex.getStackTrace().toString());
        }

        return getOkResponse(hasChanged);
    }

    @GET
    @Path("logout/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@PathParam("id") int id){
        boolean response = manager.logOut(getAccountManager().getAccount(id).getEmail());
        return getOkResponse(response);
    }

}
