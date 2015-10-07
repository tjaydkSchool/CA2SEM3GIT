package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Person;
import exceptions.PersonNotFoundException;
import facade.PersonFacade;
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

@Path("person")
public class PersonRestService {

    @Context
    private UriInfo context;
    Gson gson;
    PersonFacade pf;
    
    public PersonRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        pf = new PersonFacade();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getPersonById(@PathParam("id") long id) throws PersonNotFoundException {
        return gson.toJson(pf.getPersonById(id));
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createPerson(String person) {
        Person p = gson.fromJson(person, Person.class);
        pf.createPerson(p);
        return person;
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public String updatePerson(@PathParam("id")long id, String person) throws PersonNotFoundException {
        Person p = pf.getPersonById(id);
        p = gson.fromJson(person, Person.class);
        pf.updatePerson(p);
        return gson.toJson(p);
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public String deletePerson(@PathParam("id")long id) throws PersonNotFoundException{
        Person p = pf.getPersonById(id);
        pf.deletePerson(p);
        return gson.toJson(p);
    }
}
