package org.fit.pis.api;

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
import org.fit.pis.data.Rozhodci;
import org.fit.pis.data.RozhodciZapa;
import org.fit.pis.data.Sestava;
import org.fit.pis.data.SestavaHrac;
import org.fit.pis.data.Stridani;
import org.fit.pis.data.Tym;
import org.fit.pis.data.Zapa;
import org.fit.pis.service.TymManager;
import org.fit.pis.service.ZapaManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * TEST URL:
 * http://localhost:8080/jsf-basic/rest/search
 */
@Stateless
@Path("/search")
public class SearchAPI 
{
	@EJB
	private ZapaManager zapaMgr; 
	@EJB
	private TymManager tymMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public SearchAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @SuppressWarnings("unchecked")
	@Path("/{string}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<JSONObject> getJsonSingle(@PathParam("string") String string) throws NamingException 
    {
    	JSONObject zapasJson = new JSONObject();
    	JSONObject tymyJson = new JSONObject();
    	JSONObject zapasyJson = new JSONObject();
    	JSONArray allJson = new JSONArray();
    	JSONArray zapasypoleJson = new JSONArray();
    	JSONArray tymypoleJson = new JSONArray();
    	
    	
    	for(Tym tym : tymMgr.Search(string)) {
    		if(tym != null) {
        		JSONObject tymJson = new JSONObject();
        		tymJson.put("id", tym.getId());
        		tymJson.put("nazev", tym.getNazev());
        		tymypoleJson.add(tymJson);
        	}
    	}
    	tymyJson.put("Tymy", tymypoleJson);
    	allJson.add(tymyJson);
    	
    	/* TODO DAGO
    	for(Zapa zapas : zapaMgr.findAll()) {

    			Boolean flag = false;
				String pattern = "hh:mm dd.MM";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(zapas.getDatum());
				
				

				for(Sestava sestava: zapas.getSestavas())
				{
					JSONObject sestavaJson = new JSONObject();
					
					sestavaJson.put("kapitan", sestava.getKapitan_id());
					sestavaJson.put("tym", sestava.getTym().getNazev());		
					
					
					//domaci=0
					if(sestava.getHostujici()==0)
					{
						zapasJson.put("domaci", sestavaJson);	
					}
					else
					{
						zapasJson.put("hoste", sestavaJson);
					}

					if(sestava.getTym().getNazev().contains(string)) {
						flag = true;
					}
				}
				if(flag) {	
					zapasJson.put("id",zapas.getId());
					zapasJson.put("datum",date);
					zapasJson.put("domaci_goly",zapas.getDomaci_tym_skore());
					zapasJson.put("hoste_goly",zapas.getHost_tym_skore());
					zapasJson.put("pocet_divaku",zapas.getPocet_divaku());
					zapasJson.put("stadion",zapas.getStadion());
					zapasJson.put("skupina",zapas.getSkupina());
					zapasypoleJson.add(zapasJson);
				}
	    	}
    	
    	zapasyJson.put("Zapasy",zapasypoleJson);
    	allJson.add(zapasyJson);
    	*/
    	
    	return allJson;
    }
}
    	   	