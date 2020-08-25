package app.single;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client
 */
public class Client {
  static boolean quit = false;
  static final int port = 8081;
  // localhost || 127.0.0.1 || ip_address(192.168.1.4)
  static final String hostname = "localhost";

  public static void main(String[] args) {
    System.out.println("client has started");
    try (Socket socket = new Socket(hostname, port);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(input));
      // BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
      // PrintWriter pr = new PrintWriter(output, true);
      // pr.println("Hello there with a new line!");
      Console console = System.console();
      while (!quit) {
        String message = console.readLine();
        if (message.equals("quit")) {
          quit = true;
          System.out.println("quitting client");
        }
        System.out.printf("Me: %s\n", message);
        // send it out to the server
        output.write((message + "\n").getBytes());
        // read the server's message
        String serverMessage = !quit ? reader.readLine() : "";
        // print out the server's message
        System.out.printf("%s\n", serverMessage);
      }
    } catch (IOException ex) {
      System.out.println("Error getting input stream: " + ex.getMessage());
      ex.printStackTrace();
      // handle suppressed exceptions
    }
  }
}