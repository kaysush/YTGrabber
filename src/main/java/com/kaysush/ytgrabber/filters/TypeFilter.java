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
import javax.servlet.http.HttpServletResponse;

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
        HttpServletResponse res = (HttpServletResponse)response;
        if(type == null)
            type = "json";
            switch(type){
                case "json" :
                    System.out.println("--------------------------");
                    System.out.println("Setting JSON");
                    System.out.println("--------------------------");
                    res.addHeader("Accept", "application/json");
                break;
                
                case "xml" : 
                    System.out.println("--------------------------");
                    System.out.println("Setting XML");
                    System.out.println("--------------------------");
                    res.addHeader("Accept", "application/xml");
                break;
                
                default:
                    System.out.println("--------------------------");
                    System.out.println("Setting DEFAULT");
                    System.out.println("--------------------------");
                    res.addHeader("Accept", "application/json");
            }
        
            chain.doFilter(request, res);
    }

    @Override
    public void destroy() {
        System.out.println("Filter Destroyed");
    }
    
}
