package com.kaysush.ytgrabber.yt;

import java.util.ArrayList;

/**
 *
 * @author Sushil Kumar <kaysush@outlook.com>
 */
public class DownloadResource {

    private ArrayList<DownloadLink> links;
    
    public DownloadResource(){
        this.links = new ArrayList<DownloadLink>();
    }
    
    public void addLink(String url, String quality, String format){
        this.links.add(new DownloadLink(url, quality, format));
    }
    
    public String toString(){
        return links.toString();
    }
    
}
