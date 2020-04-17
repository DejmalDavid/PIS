package org.fit.pis.api;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import org.fit.pis.data.Gol;
import org.fit.pis.data.Hrac;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.data.Stridani;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.HracManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Hrac/list
 */
@Stateless
@Path("/hrac")
public class HracAPI 
{
	@EJB
	private HracManager hracMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public HracAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Hrac> getJson() throws NamingException 
    {
    	System.out.println("API3");
    	return hracMgr.findAll();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Hrac p = hracMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @SuppressWarnings("unchecked")
	@Path("/team/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getByTeam(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	JSONArray array = new JSONArray();
    	for(Hrac hrac:hracMgr.findAll())
    	{
    		//TODO if team==id
    		if(hrac.getTym().getId()==Integer.valueOf(idString))
    		{
    		JSONObject hracJson = new JSONObject();
    		hracJson.put("name", hrac.getJmeno()+" "+ hrac.getPrijmeni());
    		hracJson.put("position" , hrac.getPozice());
    		hracJson.put("vek", hrac.getVek());
    		int pocetZapasu=0;
    		
			for (int i = 0; i < hrac.getSestavaHracs().size(); i++) {
				pocetZapasu++;
			} 
    		hracJson.put("matches",pocetZapasu);
    		
    		int pocetGolu=0;
			for (int i = 0; i < hrac.getGols1().size(); i++) {
				pocetGolu++;
			} 		
    		hracJson.put("goals",pocetGolu);
    		
    		int assist=0;
			for (int i = 0; i <  hrac.getGols2().size(); i++) {
				assist++;
			}
    		hracJson.put("assist",assist);
    		
    		
    		
    		array.add(hracJson);
    		}
    	}
    	
    	return array;
    }
        
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Hrac> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Hrac person)
    {
    	hracMgr.save(person);
    	return "ok";
    }
    
    @SuppressWarnings("unchecked")
	@Path("/stats/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getStats(@PathParam("id") int id) throws NamingException 
    {
    	Hrac h = hracMgr.find(id);
    	JSONArray array = new JSONArray();
    	JSONObject zapasJson = new JSONObject();
    	
    	int zapasu = 0;
    	int golu = 0;
    	int assists = 0;
    	
    	for(SestavaHrac sh : h.getSestavaHracs())
    	{
        		zapasu++;
    	}
        for(Gol g : h.getGols1())
        {
        		golu++;
        	}
        for(Gol g : h.getGols2())
        {
        		assists++;
        	}
        
        zapasJson.put("tym", h.getTym().getNazev());
        zapasJson.put("jmeno", h.getJmeno());
        zapasJson.put("prijmeni", h.getPrijmeni());
        zapasJson.put("vek", h.getVek());
        zapasJson.put("cislo", h.getPozice());
        zapasJson.put("skill", h.getSkill());
        zapasJson.put("zapasu",zapasu);
		zapasJson.put("golu", golu);
		zapasJson.put("assist", assists);
        			
    	//neni prazdny
    	if(zapasJson.isEmpty()==false)
    	{
    		array.add(zapasJson);
    	}
    	
    	
    	return array;
    }

}