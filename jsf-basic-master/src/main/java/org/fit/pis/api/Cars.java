/**
 * 
 */
package org.fit.pis.api;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.fit.pis.data.Car;
import org.fit.pis.service.CarManager;

@Stateless
@Path("/cars")
public class Cars 
{
	@EJB
	private CarManager carMgr;
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public Cars() 
    {
    }

    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getJson() throws NamingException 
    {
    	return carMgr.findAll();
    }

    /**
     * PUT method for updating or creating an instance of People
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(List<Car> content) 
    {
    }


}
