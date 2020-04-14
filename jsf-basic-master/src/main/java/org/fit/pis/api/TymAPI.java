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

import org.fit.pis.data.Gol;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.Tym;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.TymManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/Tym/list
 */
@Stateless
@Path("/tym")
public class TymAPI 
{
	@EJB
	private TymManager tymMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public TymAPI() 
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
    	int[] skupiny = {1,2,3,4,5,6,7,8};


    	for(int skupina : skupiny)
    	{
    		
    		JSONObject skupinaJson = new JSONObject();		
    		JSONArray pole = new JSONArray();
    		
    		for (Tym tym :  tymMgr.findAll()) {
    			
    			if(skupina==tym.getSkupina())
				{
                	JSONObject tymJson = new JSONObject();
                	tymJson.put("id", tym.getId());	   
                	tymJson.put("name", tym.getNazev() );
                	
                	int body = 0;
                	int golDostane = 0;
                	int golDane= 0;
                	int zapasy= 0;
                	//TODO - hledam pocet zapasu a pocet vyher(na body)
                	for(Sestava sestava: tym.getSestavas())
                	{
                		//TODO - domaci == 0 = true
                		if(sestava.getHostujici()==0)
                		{
                			//Jsem domaci
                        	Zapa zapas=sestava.getZapa();
                        	zapasy++;
                        	golDostane+=zapas.getHost_tym_skore();
                        	golDane+=zapas.getDomaci_tym_skore();
                        	//vyhra
                        	if(zapas.getDomaci_tym_skore()>zapas.getHost_tym_skore())
                        	{
                        		body+=3;
                        	}
                        	//remiza
                        	else if(zapas.getDomaci_tym_skore()==zapas.getHost_tym_skore())
                        	{
                        		body+=1;
                        	}
                        	//prohra takze +0
                		}
                		else if(sestava.getHostujici()==1)
                		{
                			//sem hostujici
                        	Zapa zapas=sestava.getZapa();
                        	zapasy++;
                        	golDane+=zapas.getHost_tym_skore();
                        	golDostane+=zapas.getDomaci_tym_skore();
                        	//vyhra
                        	if(zapas.getDomaci_tym_skore()<zapas.getHost_tym_skore())
                        	{
                        		body+=3;
                        	}
                        	//remiza
                        	else if(zapas.getDomaci_tym_skore()==zapas.getHost_tym_skore())
                        	{
                        		body+=1;
                        	}
                        	//prohra takze +0
                		}

                	}      
                	tymJson.put("zapasy", zapasy );
                	tymJson.put("score", golDane+":"+golDostane );
                	tymJson.put("body", body );
                	//pridani do pole
                	pole.add(tymJson);
				}
    		   					
        	} 
    		
    		skupinaJson.put(String.valueOf(skupina), pole);	   		
    		array.add(skupinaJson);	
    	}
    	
    	return array;

    }

    
    @SuppressWarnings("unchecked")
	@Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getByGroup(@PathParam("id") String idString) throws NamingException 
    {
    	   	
    	JSONArray array = new JSONArray();   	

    	int skupina = Integer.valueOf(idString);

    		JSONObject skupinaJson = new JSONObject();		
    		JSONArray pole = new JSONArray();
    		
    		for (Tym tym :  tymMgr.findAll()) {
    			
    			if(skupina==tym.getSkupina())
				{
                	JSONObject tymJson = new JSONObject();
                	tymJson.put("id", tym.getId());	   
                	tymJson.put("name", tym.getNazev() );
                	
                	int body = 0;
                	int golDostane = 0;
                	int golDane= 0;
                	int zapasy= 0;
                	//TODO - hledam pocet zapasu a pocet vyher(na body)
                	for(Sestava sestava: tym.getSestavas())
                	{
                		//TODO - domaci == 0 = true
                		if(sestava.getHostujici()==0)
                		{
                			//Jsem domaci
                        	Zapa zapas=sestava.getZapa();
                        	zapasy++;
                        	golDostane+=zapas.getHost_tym_skore();
                        	golDane+=zapas.getDomaci_tym_skore();
                        	//vyhra
                        	if(zapas.getDomaci_tym_skore()>zapas.getHost_tym_skore())
                        	{
                        		body+=3;
                        	}
                        	//remiza
                        	else if(zapas.getDomaci_tym_skore()==zapas.getHost_tym_skore())
                        	{
                        		body+=1;
                        	}
                        	//prohra takze +0
                		}
                		else if(sestava.getHostujici()==1)
                		{
                			//sem hostujici
                        	Zapa zapas=sestava.getZapa();
                        	zapasy++;
                        	golDane+=zapas.getHost_tym_skore();
                        	golDostane+=zapas.getDomaci_tym_skore();
                        	//vyhra
                        	if(zapas.getDomaci_tym_skore()<zapas.getHost_tym_skore())
                        	{
                        		body+=3;
                        	}
                        	//remiza
                        	else if(zapas.getDomaci_tym_skore()==zapas.getHost_tym_skore())
                        	{
                        		body+=1;
                        	}
                        	//prohra takze +0
                		}

                	}      
                	tymJson.put("zapasy", zapasy );
                	tymJson.put("score", golDane+":"+golDostane );
                	tymJson.put("body", body );
                	//pridani do pole
                	pole.add(tymJson);
				}
    		   					
        	}
    		
    		skupinaJson.put(String.valueOf(skupina), pole);	   		
    		array.add(skupinaJson);	
    	
    	
    	return array;

    }
    
    
    
    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJsonSingle(@PathParam("id") String idString) throws NamingException 
    {
    	int id = Integer.valueOf(idString);
    	Tym p = tymMgr.find(id);
    	if (p != null)
    		return Response.ok(p).build();
    	else
    		return Response.status(Status.NOT_FOUND).entity("{\"error\": \"No such person\"}").build();
    }

    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response putJson(List<Tym> content) 
    {
    	return Response.status(Response.Status.NOT_IMPLEMENTED).entity("This is not available now").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String postJson(Tym person)
    {
    	tymMgr.save(person);
    	return "ok";
    }

}