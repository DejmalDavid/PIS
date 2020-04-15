package org.fit.pis.api;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject login(@Context final HttpServletRequest request) {
    	String email = "";
    	email = request.getParameter("username");
        String password = "";
        password = request.getParameter("password");
        JSONObject response = new JSONObject();
        
        if (uzivatelMgr.existWithEmail(email)) {
        	
        	Uzivatel foundUser = uzivatelMgr.findWithEmail(email);
        	
        	if(password.contentEquals(foundUser.getHeslo())) {
        		//final Token token = AuthUtils.createToken(request.getRemoteHost(), foundUser.get().id);
        		response.put("Success", true);
        		//return Response.status(Status.OK).entity("LOGIN_OK_MSG").build();
        	}
        	else
        		response.put("Success", false);
        		//return Response.status(Status.UNAUTHORIZED).entity("LOGIN_ERROR_MSG").build();
          }
        else
        	response.put("Success", false);
        	//return Response.status(Status.UNAUTHORIZED).entity("LOGIN_ERROR_MSG").build();
        return response;
        
    	 
    }
    
    @Path("/registration")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject Registration(@Context final HttpServletRequest request) {
    	String email = request.getParameter("username");
        String password = request.getParameter("password");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        int role = Integer.parseInt(request.getParameter("role"));
        JSONObject response = new JSONObject();
        
      
        if (uzivatelMgr.existWithEmail(email)) {
        	 response.put("Success", false);
        	 //return Response.status(Status.NOT_ACCEPTABLE).entity("REG_EXIST_MSG").build();
          }
        else {
        	Uzivatel uzivatelNew = new Uzivatel(email, password, fname, lname, role);
        	uzivatelMgr.insert(uzivatelNew);
        	response.put("Success", true);
        	//return Response.status(Status.OK).entity("REG_OK_MSG").build();
    	 
        }
        return response;
    } 

}