package org.fit.pis.api;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.naming.NamingException;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

import org.fit.pis.data.Gol;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.data.Stridani;
import org.fit.pis.models.GolOutput;
import org.fit.pis.service.GolManager;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Rozhodci/list
 */
@Stateless
@Path("/gol")
public class GolAPI 
{
	@EJB
	private GolManager golMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public GolAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @SuppressWarnings("unchecked")
	@Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getJson() throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
    	
    	for (Gol gol :  golMgr.findAll()) {
        	JSONObject golJson = new JSONObject();
        	golJson.put("id", gol.getId());	   
        	golJson.put("cas",  gol.getGol_cas());	
        	golJson.put("polovina", gol.getPolovina_zapasu());	   
        	golJson.put("zapas_id", gol.getZapa().getId());	
        	golJson.put("hrac1",  gol.getHrac1().getPrijmeni());	
        	golJson.put("hrac1_id",  gol.getHrac1().getId());
        	golJson.put("hrac2",   gol.getHrac2().getPrijmeni());	
        	golJson.put("hrac2_id",  gol.getHrac2().getId());
        	array.add(golJson);
    	}
    	
    	return array;
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Gol p = golMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Gol> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Gol gol)
    {
    	golMgr.save(gol);
    	
    	
		for(Sestava sestava: gol.getZapa().getSestavas())
		{
			for(SestavaHrac sesHrac:sestava.getSestavaHracs2())
			{
				if(sesHrac.getHrac().getId()==gol.getHrac1().getId())
				{
					if(sestava.getHostujici()==0)
					{//sem domaci
						gol.getZapa().setDomaci_tym_skore(gol.getZapa().getDomaci_tym_skore()+1);
					}
					else 
					{
						gol.getZapa().setHost_tym_skore(gol.getZapa().getHost_tym_skore()+1);
					}
				}
			}
		}
    	return "ok";
    }

    @Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGolById(@PathParam("id") int id) {
    	Gol gol = golMgr.find(id);
    	golMgr.remove(gol);
    	if (gol != null)
    	{
    		for(Sestava sestava: gol.getZapa().getSestavas())
    		{
    			for(SestavaHrac sesHrac:sestava.getSestavaHracs2())
    			{
    				if(sesHrac.getHrac().getId()==gol.getHrac1().getId())
    				{
    					if(sestava.getHostujici()==0)
    					{//sem domaci
    						gol.getZapa().setDomaci_tym_skore(gol.getZapa().getDomaci_tym_skore()-1);
    					}
    					else 
    					{
    						gol.getZapa().setHost_tym_skore(gol.getZapa().getHost_tym_skore()-1);
    					}
    				}
    			}
    		}
    	
    		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    	}
    	else
    	{
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
    	}
    	
    	
	}
    
}

