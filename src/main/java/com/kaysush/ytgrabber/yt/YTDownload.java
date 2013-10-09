/*
 * The middleware component which helps
 * to download the video whithout any
 * client side dependency.
 */
package com.kaysush.ytgrabber.yt;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kaysush
 */
public class YTDownload extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title=request.getParameter("title");
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition","attachment; filename=\""+title+"\"");
        
        String url=request.getParameter("url");
        url=url.replace('!','&');
        System.out.println(title);
        URLConnection  con = null;
        
      
        BufferedOutputStream out=new BufferedOutputStream(response.getOutputStream());
        InputStream in=null;
        byte[] buffer;
        int ByteRead,ByteWritten=0;
        try {

            URL dUrl=new URL(url);
            con=dUrl.openConnection();
            response.setContentLength(con.getContentLength());
            in=con.getInputStream();
            buffer = new byte[4*1024];
            while ((ByteRead = in.read(buffer)) != -1)
            {
                
                out.write(buffer, 0, ByteRead);
                ByteWritten += ByteRead;
                
            }

         
            
            
        } finally {            
          //  out.close();
            //in.close();
        }
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}