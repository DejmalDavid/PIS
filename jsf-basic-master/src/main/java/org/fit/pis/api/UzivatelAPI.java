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
/*
import org.fit.pis.data.OblibeneTymy;
import org.fit.pis.data.Tym;
import org.fit.pis.data.Uzivatel;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.OblibeneTymyManager;
*/
import org.fit.pis.data.Uzivatel;
import org.fit.pis.service.UzivatelManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Uzivatel/list
 */
@Stateless
@Path("/uzivatel")
public class UzivatelAPI 
{
	@EJB
	private UzivatelManager uzivatelMgr;
	//private OblibeneTymyManager oblibeneMgr;
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public UzivatelAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Uzivatel> getJson() throws NamingException 
    {
    	System.out.println("API3");
    	return uzivatelMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Uzivatel p = uzivatelMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Uzivatel> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Uzivatel person)
    {
    	uzivatelMgr.save(person);
    	return "ok";
    }
/*    
    @Path("/oblibene")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOblibene(Uzivatel uzivatel)
    {
    	oblibeneMgr.findAllByUzivatel(uzivatel.getId());
    	return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    }
    
    
    @Path("/oblibene")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOblibene(Uzivatel uzivatel, Tym tym)
    {
    	OblibeneTymy ot = null;
    	ot.setTym(tym);
    	ot.setUzivatel(uzivatel);
    	oblibeneMgr.save(ot);
    	return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    }
    
    @Path("/oblibene/{id}")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteZapaById(@PathParam("id") int id) {
    	OblibeneTymy ot = oblibeneMgr.find(id);
    	oblibeneMgr.remove(ot);
    	if (ot != null)
    		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
	}
	*/
}