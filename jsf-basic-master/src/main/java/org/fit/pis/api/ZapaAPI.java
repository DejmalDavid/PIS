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

import org.fit.pis.data.Sestava;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.ZapaManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Zapas/list
 */
@Stateless
@Path("/zapas")
public class ZapaAPI 
{
	@EJB
	private ZapaManager zapaMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public ZapaAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Zapa> getJson() throws NamingException 
    {
    	System.out.println("API3");
    	return zapaMgr.findAll();
    }
    
    @SuppressWarnings("unchecked")
	@Path("/team/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getByTeam(@PathParam("id") String idString) throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
    	for(Zapa zapas:zapaMgr.findAll())
    	{
    		JSONObject zapasJson = new JSONObject();
    		
        	for(Sestava sestava:zapas.getSestavas())
        	{
        		if(sestava.getTym().getId()==Integer.valueOf(idString))
        		{
        				
        			//0=domaci
        			if(sestava.getHostujici()==0)
        			{
        				zapasJson.put("home", sestava.getTym().getNazev());
        				zapasJson.put("datum",zapas.getDatum());
        				zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
        			}
        			//1=hoste
        			else if(sestava.getHostujici()==1)
        			{
        				zapasJson.put("away", sestava.getTym().getNazev());
        			}	
        		}
        		
        	}
        	//neni prazdny
        	if(zapasJson.isEmpty()==false)
        	{
        		array.add(zapasJson);
        	}
      	
        	
    	}
    	
    	
    	return array;
    }
    

    @SuppressWarnings("unchecked")
	@Path("/group/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getByGroup(@PathParam("id") String idString) throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
    	for(Zapa zapas:zapaMgr.findAll())
    	{
    		JSONObject zapasJson = new JSONObject();
    		
        	for(Sestava sestava:zapas.getSestavas())
        	{
        		if(sestava.getTym().getSkupina()==Integer.valueOf(idString))
        		{
        				
        			//0=domaci
        			if(sestava.getHostujici()==0)
        			{
        				zapasJson.put("home", sestava.getTym().getNazev());
        				zapasJson.put("datum",zapas.getDatum());
        				zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
        			}
        			//1=hoste
        			else if(sestava.getHostujici()==1)
        			{
        				zapasJson.put("away", sestava.getTym().getNazev());
        			}	
        		}
        		
        	}
        	//neni prazdny
        	if(zapasJson.isEmpty()==false)
        	{
        		array.add(zapasJson);
        	}
      	
        	
    	}
    	
    	
    	return array;
    }
    
    
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Zapa p = zapaMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Zapa> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Zapa person)
    {
    	zapaMgr.save(person);
    	return "ok";
    }

}