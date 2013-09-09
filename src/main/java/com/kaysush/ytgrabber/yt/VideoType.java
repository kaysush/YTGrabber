package com.kaysush.ytgrabber.yt;

/**
 *
 * @author Sushil Kumar <kaysush@outlook.com>
 */
public class VideoType {

    private String quality;
    private String format;

    public VideoType(String quality, String format) {
        this.quality = quality;
        this.format = format;
    }
    
    public String getQuality(){
        return this.quality;
    }
    
    public String getFormat(){
        return this.format;
    }
    
    public String toString(){
        return this.format + "/" + this.quality;
    }
}
