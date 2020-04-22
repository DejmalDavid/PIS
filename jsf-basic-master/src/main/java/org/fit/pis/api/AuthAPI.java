package org.fit.pis.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.fit.pis.back.UzivatelBean;
import org.fit.pis.data.Uzivatel;
import org.fit.pis.service.UzivatelManager;
import org.json.simple.JSONObject;

/*
 * http://localhost:8080/jsf-basic/rest/auth
 */
@Stateless
@Path("/auth")
public class AuthAPI
{
	@EJB
	private UzivatelManager uzivatelMgr; 
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public AuthAPI() 
    {
    }

    @PostConstruct
    public void init()
    {
    }
    
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject login(Uzivatel u) {
    	String email = "";
    	email = u.getEmail();
        String password = "";
        password = u.getHeslo();
        JSONObject response = new JSONObject();      
        
        if (uzivatelMgr.existWithEmail(email)) {
        	
        	Uzivatel foundUser = uzivatelMgr.findWithEmail(email);
        	 
        	if(password.contentEquals(foundUser.getHeslo())) {
        		response.put("Success", true);
        	}
        	else
        		response.put("Success", false);
          }
        else
        	response.put("Success", false);
        return response;
        
    	 
    }
    
    @Path("/registration")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject Registration(Uzivatel u) {
    	String email = u.getEmail();
        String password = u.getHeslo();
        String fname =  u.getJmeno();
        String lname = u.getPrijmeni();
        int role = u.getOpravneni();
        JSONObject response = new JSONObject();
        
      
        if (uzivatelMgr.existWithEmail(email)) {
        	 response.put("Success", false);
          }
        else {
        	Uzivatel uzivatelNew = new Uzivatel(email, password, fname, lname, role);
        	uzivatelMgr.insert(uzivatelNew);
        	response.put("Success", true);   	 
        }
        return response;
    } 

}