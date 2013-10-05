/*
 * A filter which changes the accept header
 * of the user request based on url query parameter
 * before it is sent to Jersey
 */
package com.kaysush.ytgrabber.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author kaysush
 */
public class TypeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter Initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String type = request.getParameter("type");
        if(type == null)
            type = "xml";
            switch(type){
                case "json" : 
                    request.setAttribute("accept", "application/json");
                break;
                
                case "xml" : 
                    request.setAttribute("accept", "application/xml");
                break;
                
                default:
                    request.setAttribute("accept", "application/xml");
            }
        
            chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroyed");
    }
    
}
