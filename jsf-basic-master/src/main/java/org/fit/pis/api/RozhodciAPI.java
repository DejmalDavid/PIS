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
import org.fit.pis.data.Rozhodci;
import org.fit.pis.service.RozhodciManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/rozhodci/list
 */
@Stateless
@Path("/rozhodci")
public class RozhodciAPI 
{
	@EJB
	private RozhodciManager rozhodciMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public RozhodciAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/zapas/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rozhodci> getRozhodciAll(@PathParam("id") int id) throws NamingException 
    {

    	return rozhodciMgr.findAllByZapas(id);
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rozhodci> getJson() throws NamingException 
    {
    	return rozhodciMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Rozhodci r = rozhodciMgr.find(id);
    	if (r != null)
    		return Response.ok(r).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Rozhodci> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Rozhodci person)
    {
    	rozhodciMgr.save(person);
    	return "ok";
    }
    
    @Path("/{id}")
   	@DELETE
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response deleteRozhodciyById(@PathParam("id") int id) {
       	Rozhodci r = rozhodciMgr.find(id);
       	rozhodciMgr.remove(r);
       	if (r != null)
       		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
       	else
       		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
   	}

}