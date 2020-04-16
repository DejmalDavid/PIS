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
import org.fit.pis.data.Tym;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.SestavaManager;
import org.fit.pis.service.ZapaManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/zapas/list
 */
@Stateless
@Path("/zapas")
public class ZapaAPI 
{
	@EJB
	private ZapaManager zapaMgr; 
	@EJB
	private SestavaManager sestavaMgr; 
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
    public Response getJsonSingle(@PathParam("id") int id) throws NamingException 
    {
    	Zapa zapas = zapaMgr.find(id);
    	if (zapas != null)
    		return Response.ok(zapas).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(Zapa zapas)
    {
    	zapaMgr.save(zapas);
    	return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    }
    
    @Path("/{id}")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteZapaById(@PathParam("id") int id) {
    	Zapa zapas = zapaMgr.find(id);
    	zapaMgr.remove(zapas);
    	if (zapas != null)
    		return Response.status(Status.OK).entity("{\"Success\": \"true\"}").build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"Success\": \"false\"}").build();
	}
    
    @SuppressWarnings("unchecked")
	@Path("/zapas/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getAllZapas() throws NamingException 
    {
    	   	
    	JSONArray array = new JSONArray();   	
    	JSONObject domaciJson = new JSONObject();
		JSONObject hosteJson = new JSONObject();

    	for(Zapa z :  zapaMgr.findAll())
    	{    	
    		JSONArray arrayZapa = new JSONArray(); 
    		JSONObject zapaJson = new JSONObject();
    		JSONObject tym1Json = new JSONObject();
			JSONObject tym2Json = new JSONObject();


    		for (Sestava s : sestavaMgr.findAll()) {
    			//Tym t = s.getTym();
    			if (s.getHostujici() == 1)
    				tym1Json.put("s", s.getZapa().getId());
    				//tym1Json.put("s", s.getId());
    			/*
    			tym1Json.put("zid", z.getId());
    			if((s.getZapa().getId() == z.getId()) && (s.getHostujici() == 0)) {
    				tym1Json.put("ID", t.getId());
    			}
    			else if(s.getHostujici() == 1) {
    				tym2Json.put("ID", t.getId());	
    			}
    			*/
    		}
    		domaciJson.put("Domaci", tym1Json);
			hosteJson.put("Hoste", tym2Json);
			arrayZapa.add(domaciJson);
			arrayZapa.add(hosteJson);
			zapaJson.put(z.getId(), arrayZapa);

			array.add(zapaJson);
    	}
		
		return array;
    }

}