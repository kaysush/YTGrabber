
package com.kaysush.ytgrabber.jersey;

import com.kaysush.ytgrabber.yt.DownloadResource;
import com.kaysush.ytgrabber.yt.YTWrapper;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * This class is the main interface for Jersey REST service.
 * It contains all the methods for interaction with the API.
 * @author Sushil Kumar <kaysush@outlook.com>
 */
@Path("/app")
public class MainApp {
    @Context ServletContext context;
            
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testEndPoint(){
        return "This is end-point test. Working fine.";
    }
    
    @GET
    @Path("/get/{vid}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public DownloadResource app(@PathParam("vid") String vid){
        String prefix = context.getInitParameter("urlprefix");
        return YTWrapper.getLinks(vid,prefix);
    }
    
}
