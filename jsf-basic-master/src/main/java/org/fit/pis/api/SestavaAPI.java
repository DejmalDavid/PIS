package org.fit.pis.api;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.service.SestavaManager;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Sestava/list
 */
@Stateless
@Path("/sestava")
public class SestavaAPI 
{
	@EJB
	private SestavaManager sestavaMgr;
	//private OblibeneTymyManager oblibeneMgr;
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public SestavaAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sestava> getJson() throws NamingException 
    {
    	System.out.println("API3");
    	return sestavaMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Sestava p = sestavaMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such sestava\"}").build();
    }

    
    @Path("/zapas/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getByMatch(@PathParam("id") String idString) throws NamingException 
    {
    	JSONObject sestavaJson = new JSONObject();
		for(Sestava sestava:sestavaMgr.findAll())
		{
			for(SestavaHrac sesHrac:sestava.getSestavaHracs2())
			{
				System.out.println("Ahoj");
				
			}
		}
    	return sestavaJson;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Sestava> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Sestava sestava)
    {
    	sestavaMgr.save(sestava);
    	return "ok";
    }

}