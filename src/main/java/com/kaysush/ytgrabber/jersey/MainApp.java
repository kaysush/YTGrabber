
package com.kaysush.ytgrabber.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * This class is the main interface for Jersey REST service.
 * It contains all the methods for interaction with the API.
 * @author Sushil Kumar <kaysush@outlook.com>
 */
@Path("/get")
public class MainApp {
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testEndPoint(){
        return "This is Jersy Demo. It uses PLAIN TEXT media type";
    }
    
}
