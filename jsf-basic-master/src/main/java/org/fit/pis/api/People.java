package org.fit.pis.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fit.pis.data.Person;
import org.fit.pis.service.PersonManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/people/list
 */
@Stateless
@Path("/people")
public class People 
{
	@EJB
	private PersonManager personMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public People() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getJson() throws NamingException 
    {
    	return personMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	long id = Long.valueOf(idString);
    	Person p = personMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Person> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Person person)
    {
    	personMgr.save(person);
    	return "ok";
    }

}