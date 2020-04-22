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
import org.fit.pis.data.RozhodciZapa;
import org.fit.pis.service.RozhodciZapaManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/rozhodcizapas/list
 */
@Stateless
@Path("/rozhodcizapas")
public class RozhodciZapasAPI 
{
	@EJB
	private RozhodciZapaManager sMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public RozhodciZapasAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<RozhodciZapa> getJson() throws NamingException 
    {
    	return sMgr.findAll();
    }
    

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	RozhodciZapa r = sMgr.find(id);
    	if (r != null)
    		return Response.ok(r).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<RozhodciZapa> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(RozhodciZapa o)
    {
    	sMgr.save(o);
    	return "ok";
    }
    
    @Path("/{id}")
   	@DELETE
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response deleteOblibeneTymyById(@PathParam("id") int id) {
       	RozhodciZapa r = sMgr.find(id);
       	sMgr.remove(r);
       	if (r != null)
       		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
       	else
       		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
   	}

}