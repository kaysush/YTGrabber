
package com.kaysush.ytgrabber.jersey;

import com.kaysush.ytgrabber.yt.DownloadResource;
import com.kaysush.ytgrabber.yt.YTWrapper;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

/**
 * This class is the main interface for Jersey REST service.
 * It contains all the methods for interaction with the API.
 * @author Sushil Kumar <kaysush@outlook.com>
 */
@Path("/get")
public class MainApp {
    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public String testEndPoint(){
        return "This is end-point test. Working fine.";
    }
    
    @GET
    @Path("/app")
    @Produces(MediaType.APPLICATION_XML)
    public DownloadResource app(){
        return YTWrapper.extractLinks("8V8yLwjpQEU");
    }
    
}
