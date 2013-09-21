package com.kaysush.ytgrabber.yt;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sushil Kumar <kaysush@outlook.com>
 */
@XmlRootElement(name = "links")
public class DownloadResource {
    @XmlElement(name = "link")
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
