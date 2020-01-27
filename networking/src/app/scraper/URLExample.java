package app.scraper;

/**
 *
 * @author Spankie Dee
 */

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLExample {
  public static void main(String[] args) throws Exception {
    // URL
    // URLConnection
    /*
     * http://example.com
     */
    URL url = new URL("https://github.com/spankie/sqoo4-nioclass/blob/master/networking/src/app/server/Server.java");
    URLConnection myURL = url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(myURL.getInputStream()));
    String inputLine;
    String pattern = "<div id=\"js-flash-container\">(.+?)</div>";
    Pattern r = Pattern.compile(pattern);
    while ((inputLine = in.readLine()) != null) {
      // System.out.println(inputLine);
      if (inputLine.contains("js-flash-container")) {
        Matcher m = r.matcher(inputLine);
        if (m.find()) {
          System.out.println(m.group(1));
        }
      }
    }
    in.close();

  }
}
