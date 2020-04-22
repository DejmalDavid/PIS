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

import org.fit.pis.data.OblibeneTymy;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.service.SestavaHracManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/sestavahrac/list
 */
@Stateless
@Path("/sestavahrac")
public class SestavaHracAPI 
{
	@EJB
	private SestavaHracManager shMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public SestavaHracAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SestavaHrac> getJson() throws NamingException 
    {
    	System.out.println("API3");
    	return shMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	SestavaHrac p = shMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<SestavaHrac> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(SestavaHrac o)
    {
    	shMgr.save(o);
    	return "ok";
    }
    
    @Path("/{id}")
   	@DELETE
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response deleteSestavaHracById(@PathParam("id") int id) {
       	SestavaHrac ot = shMgr.find(id);
       	shMgr.remove(ot);
       	if (ot != null)
       		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
       	else
       		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
   	}

}