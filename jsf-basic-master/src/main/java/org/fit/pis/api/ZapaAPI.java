package org.fit.pis.api;

import java.text.SimpleDateFormat;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fit.pis.data.Gol;
import org.fit.pis.data.Hrac;
import org.fit.pis.data.Rozhodci;
import org.fit.pis.data.RozhodciZapa;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.data.Stridani;
import org.fit.pis.data.Tym;
import org.fit.pis.data.UpdateZapas;
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
    
    @SuppressWarnings("unchecked")
	@Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getJson() throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
    	for(Zapa zapas:zapaMgr.findAll())
    	{
    		JSONObject zapasJson = new JSONObject();
    		
			String pattern = "hh:mm dd.MM";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(zapas.getDatum());
			
			zapasJson.put("datum",date);
			zapasJson.put("id", zapas.getId());
			zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
    		
        	for(Sestava sestava:zapas.getSestavas())
        	{
			
        			//0=domaci
        			if(sestava.getHostujici()==0)
        			{
        				zapasJson.put("home", sestava.getTym().getNazev());
        			}
        			//1=hoste
        			else if(sestava.getHostujici()==1)
        			{
        				zapasJson.put("away", sestava.getTym().getNazev());
        			}	
        		
        	}
        	array.add(zapasJson);      	
    	}
    	
    	
    	return array;
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
        				
                	for(Sestava ses:zapas.getSestavas())
                	{ //cyklime a cyklime - n^k^k slozitost , uuu sexy
            			//0=domaci
            			if(ses.getHostujici()==0)
            			{
            				zapasJson.put("home", ses.getTym().getNazev());
            				
            				String pattern = "hh:mm dd.MM";
            				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            				String date = simpleDateFormat.format(zapas.getDatum());
            				
            				zapasJson.put("datum",date);
            				zapasJson.put("id", zapas.getId());
            				zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
            			}
            			//1=hoste
            			else if(ses.getHostujici()==1)
            			{
            				zapasJson.put("away", ses.getTym().getNazev());
            			}	             		
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
	@Path("/faze")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getFaze() throws NamingException 
    {
    	JSONArray array = new JSONArray();
    	
    	int[] skupiny = {10,11,12,13,14};

    	for(int skupina : skupiny)
    	{
    		JSONArray pole = new JSONArray();
    		JSONObject skupinaJson = new JSONObject();
    	
	    	for(Zapa zapas:zapaMgr.findAll())
	    	{	
	    		if(zapas.getSkupina()==skupina)
	    		{
	    			JSONObject zapasJson = new JSONObject();
       				String pattern = "hh:mm dd.MM";
    				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
    				String date = simpleDateFormat.format(zapas.getDatum());
    				zapasJson.put("datum",date);
    				zapasJson.put("id", zapas.getId());
    				zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
    				
    				String home = "Domaci";
    				String away = "Hoste";
    				
	    			for(Sestava sestava:zapas.getSestavas())
	            	{
	            			//0=domaci
	            			if(sestava.getHostujici()==0)
	            			{
	            				home= sestava.getTym().getNazev();
	            			}
	            			//1=hoste
	            			else if(sestava.getHostujici()==1)
	            			{
	            				away=sestava.getTym().getNazev();
	            			}	
	            	}
	    			zapasJson.put("home", home);
	    			zapasJson.put("away", away);
	    			pole.add(zapasJson);
	            	}
	    		}
	    	
	    	skupinaJson.put(String.valueOf(skupina), pole);	
	    	array.add(skupinaJson);	
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
    		if(zapas.getSkupina()==9)
    		{
    			for(Sestava sestava:zapas.getSestavas())
            	{
            		if(sestava.getTym().getSkupina()==Integer.valueOf(idString))
            		{
            				
            			//0=domaci
            			if(sestava.getHostujici()==0)
            			{
            				zapasJson.put("home", sestava.getTym().getNazev());
            				String pattern = "hh:mm dd.MM";
            				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            				String date = simpleDateFormat.format(zapas.getDatum());
            				
            				zapasJson.put("datum",date);
            				zapasJson.put("id", zapas.getId());
            				zapasJson.put("score", zapas.getDomaci_tym_skore()+":"+zapas.getHost_tym_skore());
            			}
            			//1=hoste
            			else if(sestava.getHostujici()==1)
            			{
            				zapasJson.put("away", sestava.getTym().getNazev());
            			}	
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
	@Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject getJsonSingle(@PathParam("id") int idString) throws NamingException 
    {
    	JSONObject zapasJson = new JSONObject();
    	
    	int id = Integer.valueOf(idString);
    	Zapa zapas = zapaMgr.find(id);
    	
    	if(zapas==null)
    	{
    		return zapasJson;
    	}
    	else
    	{
			String pattern = "hh:mm dd.MM";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(zapas.getDatum());
			
			zapasJson.put("datum",date);
			zapasJson.put("domaci_goly",zapas.getDomaci_tym_skore());
			zapasJson.put("hoste_goly",zapas.getHost_tym_skore());
			zapasJson.put("pocet_divaku",zapas.getPocet_divaku());
			zapasJson.put("stadion",zapas.getStadion());
			zapasJson.put("skupina",zapas.getSkupina());
			
			JSONArray poleGolu = new JSONArray();
			
			for(Gol gol: zapas.getGols())
			{
	        	JSONObject golJson = new JSONObject();
	        	golJson.put("id", gol.getId());	   
	        	golJson.put("cas",  gol.getGol_cas());	
	        	golJson.put("polovina", gol.getPolovina_zapasu());	   
	        	golJson.put("zapas-id", gol.getZapa().getId());	
	        	golJson.put("hrac1",  gol.getHrac1().getPrijmeni());	   
	        	golJson.put("hrac2",   gol.getHrac2().getPrijmeni());
	        	golJson.put("id_team", gol.getHrac1().getTym().getId());
				poleGolu.add(golJson);
			}
			
			zapasJson.put("Goly", poleGolu);

			JSONArray poleStridani= new JSONArray();
			
			for(Stridani stridani: zapas.getStridanis())
			{
				JSONObject stridaniJson = new JSONObject();
				
				stridaniJson.put("cas", stridani.getCas());
				stridaniJson.put("id_in",stridani.getHrac_id_in());
				stridaniJson.put("id_out",stridani.getHrac_id_out());
				stridaniJson.put("id_stridani",stridani.getId());
				poleStridani.add(stridaniJson);
			}
			
			zapasJson.put("Stridani", poleStridani);
					
			JSONArray poleRozhodni= new JSONArray();
			
			for(RozhodciZapa rozhodciZap: zapas.getRozhodciZapas())
			{
				JSONObject rozJson = new JSONObject();
				Rozhodci rozhodci= rozhodciZap.getRozhodci();
				
				rozJson.put("jmeno",rozhodci.getJmeno() +" " + rozhodci.getPrijmeni());
				rozJson.put("zeme",rozhodci.getZeme());			
				poleRozhodni.add(rozJson);
			}
			
			zapasJson.put("Rozhodci", poleRozhodni);
			
			
			for(Sestava sestava: zapas.getSestavas())
			{
				JSONObject sestavaJson = new JSONObject();
				
				sestavaJson.put("kapitan", sestava.getKapitan_id());
				sestavaJson.put("tym", sestava.getTym().getNazev());		
				sestavaJson.put("id_team", sestava.getTym().getId());
				
				JSONArray poleHraci= new JSONArray();
				for(SestavaHrac sesHrac:sestava.getSestavaHracs2())
				{
					Hrac hrac = sesHrac.getHrac();
					JSONObject hracJson = new JSONObject();
		    		hracJson.put("name", hrac.getJmeno()+" "+ hrac.getPrijmeni());
		    		hracJson.put("position" , hrac.getPozice());
		    		hracJson.put("vek", hrac.getVek());
		    		hracJson.put("id", hrac.getId());
		    		
					poleHraci.add(hracJson);	
				}
				sestavaJson.put("sestava", poleHraci);
				
				//domaci=0
				if(sestava.getHostujici()==0)
				{
					zapasJson.put("domaci", sestavaJson);	
				}
				else
				{
					zapasJson.put("hoste", sestavaJson);
				}
			}
			
			
			
			
    	}
	
    	return zapasJson;
    	
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
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postUpdateJson(@PathParam("id") int id, UpdateZapas uz)
    {
    	Zapa zapas = zapaMgr.find(id);
    	zapas.setStadion(uz.stadion);
    	zapas.setPocet_divaku(uz.pocet_divaku);
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
    
}