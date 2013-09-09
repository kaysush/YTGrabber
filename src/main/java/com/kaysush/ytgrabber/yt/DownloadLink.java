package com.kaysush.ytgrabber.yt;

/**
 *
 * @author Sushil Kumar <kaysush@outlook.com>
 */
public class DownloadLink {

    private String url;
    private VideoType type;

    public DownloadLink(String url, String quality, String format) {
        this.url = url;
        this.type = new VideoType(quality, format);
    }
    
    public String getUrl(){
        return this.url;
    }
    
    public VideoType getType(){
        return this.type;
    }
    
    public String toString(){
        return "URL : " + this.url + "\n VideoType : " + this.type;
    }
}
