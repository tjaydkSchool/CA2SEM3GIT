/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Company;
import facade.CompanyFacade;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
@Path("company")
public class CompanyRest {

    Gson gson;
    CompanyFacade cfacade;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CompanyRest
     */
    public CompanyRest() {
        gson = new GsonBuilder().setPrettyPrinting().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create();
        cfacade = new CompanyFacade();
    }
    
    @GET
    @Path("cvr/{number}")
    @Produces("application/json")
    public String getCompanyByCvr(@PathParam("number") String cvr){
        return gson.toJson(cfacade.getCompanyByCVR(cvr));
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getCompanyById(@PathParam("id") long id){
        return gson.toJson(cfacade.getCompanyById(id));
    }
    
    @GET
    @Path("phone/{number}")
    @Produces("application/json")
    public String getCompanyByPhone(@PathParam("number") String number){
        return gson.toJson(cfacade.getCompanyByPhone(number));
    }
    
    @GET
    @Path("employees/{number}")
    @Produces("application/json")
    public String getCompaniesByWithXEmployees(@PathParam("number") int number){
        return gson.toJson(cfacade.getCompaniesWithMoreThanXEmployees(number));
    }
    
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public String createCompany(String company){
        Company c = gson.fromJson(company, Company.class);
        cfacade.createCompany(c);
        return company;
//        cfacade.createCompany(gson.fromJson(company, Company.class));
    }
    
    @PUT
    @Consumes("application/json")
    public void updateCompany(String company){
        cfacade.updateCompany(gson.fromJson(company, Company.class));
    }
    
    @DELETE
    @Path("{id}")
    @Consumes("application/json")
    public void deleteCompany(@PathParam("id") String company){
        cfacade.deleteCompany(gson.fromJson(company, Company.class));
    }
    

    
}
