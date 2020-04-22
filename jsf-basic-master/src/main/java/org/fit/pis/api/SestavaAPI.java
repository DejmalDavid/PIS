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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fit.pis.data.OblibeneTymy;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.service.SestavaManager;
import org.json.simple.JSONArray;
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

    

    @SuppressWarnings("unchecked")
	//@Path("/zapas/team/{id_team}{id_zapas}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getByIDs(@QueryParam("id_team") String idTeam,@QueryParam("id_zapas") String idZapas) throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
		for(Sestava sestava:sestavaMgr.findAll())
		{
			if(sestava.getTym().getId()==Integer.valueOf(idTeam))
			{
				if(sestava.getZapa().getId()==Integer.valueOf(idZapas))
				{
					JSONObject sestavaJson = new JSONObject();
					sestavaJson.put("id_sestava", sestava.getId());
					sestavaJson.put("id_team", sestava.getTym().getId());
					sestavaJson.put("team_name", sestava.getTym().getNazev());
					sestavaJson.put("id_zapas", sestava.getZapa().getId());
					sestavaJson.put("hoste", sestava.getHostujici());
					array.add(sestavaJson);
				}
			}
		}
    	return array;
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
    
    @Path("/{id}")
   	@DELETE
   	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
   	public Response deleteSestavaById(@PathParam("id") int id) {
       	Sestava ot = sestavaMgr.find(id);
       	sestavaMgr.remove(ot);
       	if (ot != null)
       		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
       	else
       		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
   	}

}