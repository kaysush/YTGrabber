/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kaysush.ytgrabber.yt;

import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.proxy.ProxyServlet;


/**
 *
 * @author SUSHIL
 */
public class DownloaderProxy extends ProxyServlet {
    
    @Override
    protected URI rewriteURI(HttpServletRequest request) {
    String url = request.getParameter("url");
    url=url.replace('!','&');
    return URI.create(url);
}
    
    @Override
    protected void customizeProxyRequest(Request proxyRequest,
        HttpServletRequest request) {
    proxyRequest.getHeaders().remove("Host");
}


}
