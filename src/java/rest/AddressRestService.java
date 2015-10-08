/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Address;
import facade.AddressFacade;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Asnorrason
 */
@Path("address")
public class AddressRestService {

    Gson gson;
    AddressFacade afacade;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AddressRestService
     */
    public AddressRestService() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        afacade = new AddressFacade();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String createAddress(String address) {
        Address a = gson.fromJson(address, Address.class);
        afacade.createAddress(a);
        return address;
    }

    @GET
    @Path("{street}")
    @Produces("application/json")
    public String getAddressByStreet(@PathParam("street") String street) {
        return gson.toJson(afacade.getAddressesByStreet(street));
    }

    @GET
    @Path("all")
    @Produces("application/json")
    @Consumes("application/json")
    public String getAllAddresses() {
        return gson.toJson(afacade.getAllAddresses());
    }

    @PUT
    @Consumes("application/json")
    public void updateAddress(String address) {
        afacade.updateAddress(gson.fromJson(address, Address.class));
    }

    @DELETE
    @Path("{street}")
    @Consumes("application/json")
    public void deleteAddress(@PathParam("street") String address) {
        afacade.deleteAddresses(gson.fromJson(address, Address.class));
    }

}
