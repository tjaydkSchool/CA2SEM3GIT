package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Hobby;
import facade.HobbyFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

@Path("hobby")
public class HobbyRestService {
    
    Gson gson;
    HobbyFacade hf;

    @Context
    private UriInfo context;

    public HobbyRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        hf = new HobbyFacade();
    }

    @GET
    @Path("all")
    @Produces("application/json")
    public String getListOfAllHobbies() {
        return gson.toJson(hf.getListOfAllHobbies());
    }
    
    @GET
    @Path("{hobby}")
    @Produces("application/json")
    public String getHobbyByName(@PathParam("hobby") String hobbyName) {
        return gson.toJson(hf.getHobbyByName(hobbyName));
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createHobby(String content) {
        Hobby hobby = gson.fromJson(content, Hobby.class);
        hf.createHobby(hobby);
        return content;
    }
    
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public String updateHobby(String content) {
        Hobby hobby = gson.fromJson(content, Hobby.class);
        hf.updateHobby(hobby);
        return content;
    }
    
    @DELETE
    @Path("{hobby}")
    @Consumes("application/json")
    @Produces("application/json")
    public String deleteHobby(@PathParam("hobby") String content) {
        Hobby hobby = gson.fromJson(content, Hobby.class);
        hf.deleteHobby(content);
        return content;
    }
}
