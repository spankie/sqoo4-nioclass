package app.inet;

import java.io.*;
import java.net.*;

/**
 *
 * @author Spankie Dee
 */
public class InetAddressExample {
  public static void main(String[] args) {
    try {
      // 127.0.0.1:3306
      // 0.0.0.0:3306
      InetAddress address = InetAddress.getLocalHost();
      System.out.printf("host address of localhost: %s\n", address.getHostAddress());
      System.out.printf("host name of localhost: %s\n\n\n", address.getHostName());


      InetAddress address2 = InetAddress.getByName("google.com");
      System.out.printf("host address of google.com: %s\n", address2.getHostAddress());
      System.out.printf("host name of google.com: %s\n", address2.getHostName());
      
    } catch (IOException e) {
      System.out.println(e.toString());
    }
  }
}