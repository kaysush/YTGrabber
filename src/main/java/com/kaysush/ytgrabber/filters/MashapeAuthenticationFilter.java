/*
 * Authenticates wether request is coming from 
 * Mashape's infrastructure or not just to make sure
 * that api is not exploited.
 * It checks the Proxy Header that is set by Mashape in every request
 * that goes through it.
 */
package com.kaysush.ytgrabber.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kaysush
 */
public class MashapeAuthenticationFilter implements Filter {
    
    private String proxyHeader;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        proxyHeader = filterConfig.getInitParameter("proxy-header");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestHeader = httpRequest.getHeader("X-Mashape-Proxy-Secret");
        
        if(requestHeader == null || !requestHeader.equals(proxyHeader)){
            httpResponse.sendError(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
    
    
}
