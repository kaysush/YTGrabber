package com.kaysush.ytgrabber.yt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sushil Kumar <kaysush@outlook.com>
 */
public class YTWrapper {

    public static DownloadResource getLinks(String vid,String prefix) {
        return extractLinks(vid,prefix);
    }

    private static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();
        for (String param : params) {
            String[] values = param.split("=");

            if (values.length > 1) {
                map.put(values[0], values[1]);
            } else {
                map.put(values[0], "NA");
            }


        }
        return map;
    }

    public static String getExtension(String type) {
        if (type.toLowerCase().equals("video/webm")) {
            return "webm";
        }
        if (type.toLowerCase().equals("video/x-flv")) {
            return "flv";
        }
        if (type.toLowerCase().equals("video/mp4")) {
            return "mp4";
        }
        if (type.toLowerCase().equals("video/3gpp")) {
            return "3gpp";
        }
        return "format";
    }

    public static String getQuality(String quality) {
        if (quality.toLowerCase().equals("small")) {
            return "240p";
        }
        if (quality.toLowerCase().equals("medium")) {
            return "360p";
        }
        if (quality.toLowerCase().equals("large")) {
            return "480p";
        }
        if (quality.toLowerCase().equals("hd720")) {
            return "720p";
        }
        if (quality.toLowerCase().equals("hd1080")) {
            return "1080p";
        }
        return "Normal";
    }

    /**
     * Returns the download links of the provided video id as a
     * {@link com.kaysush.ytgrabber.yt.DownloadResource DownloadResource}
     * object.
     *
     * @param vid id of the video
     * @return
     * {@link com.kaysush.ytgrabber.yt.DownloadResource DownloadResource} object
     * containing links and {@link com.kaysush.ytgrabber.yt.VideoType VideoType}
     * information.
     */
    private static DownloadResource extractLinks(String vid, String prefix) {
        InputStream inStream = null;
         BufferedReader in = null;
        DownloadResource links = new DownloadResource();

        try {
            URL url = new URL("http://www.youtube.com/get_video_info?video_id=" + vid);
            inStream = url.openStream();
            in= new BufferedReader(new InputStreamReader(inStream));
            String response = "", temp;
            while ((temp = in.readLine()) != null) {
                response += temp;
            }
            
            
            Map<String, String> parameters = getQueryMap(response);
            String title = parameters.get("title");
            title = URLDecoder.decode(title, "UTF-8");
            title = title.replaceAll("[^a-zA-Z0-9\\s]+", "");
            String url_encoded_fmt_stream_map = parameters.get("url_encoded_fmt_stream_map");
            url_encoded_fmt_stream_map = URLDecoder.decode(url_encoded_fmt_stream_map, "UTF-8");
            String[] urls = url_encoded_fmt_stream_map.split(",");
            for (String u : urls) {
                Map<String, String> urlParameters = getQueryMap(u);


                String type = urlParameters.get("type");
                String quality = urlParameters.get("quality");
                type = URLDecoder.decode(type, "UTF-8");
                quality = URLDecoder.decode(quality, "UTF-8");
                int index = type.indexOf(";");
                if (index >= 0) {
                    type = type.substring(0, index);
                }

                String fileName = title + "." + getExtension(type);
                String ul = urlParameters.get("url") + "&signature=" + urlParameters.get("sig");
                ul = URLDecoder.decode(ul, "UTF-8");
                ul = ul.replace("&", "!");
                ul = prefix+"/YTGrabber/YTDownload?url=" + ul + "&title=" + URLEncoder.encode(fileName, "UTF-8");
                links.addLink(ul, getQuality(quality), getExtension(type));

            }

        } catch (IOException ex) {
            Logger.getLogger(YTWrapper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inStream.close();
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(YTWrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return links;
    }
}
