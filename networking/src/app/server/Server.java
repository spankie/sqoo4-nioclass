package app.server;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class Server {
  static boolean quit = false;
  static final int port = 8081;

  public static void main(String[] args) {
    System.out.printf("Server is waiting for connection on port %d\n", port);
    try (ServerSocket serverSocket = new ServerSocket(port)) {
      // accept() blocks the execution of the code
      // until someone connects
      Socket client = serverSocket.accept();

      System.out.println("New client connected");
      // Console console = System.console();
      InputStream is = client.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      OutputStream ous = client.getOutputStream();

      // String userName = console.readLine("\nEnter your name: ");
      // ous.write(userName.getBytes());
      String message = "";
      while (!quit) {
        // when connecting with a browser, read all first before returning the response
        String newMessage = br.readLine();
        message += newMessage + "\n";
        // System.out.printf("Client: %s\n", message);
        if (message.equals("quit")) {
          message = "Thanks for chatting with me...";
          quit = true;
        } else {
          if (!client.isClosed()) {
            // "HTTP/1.1 200 OK\r\n\r\n" +
            if (newMessage.equals("") || newMessage == null) {
              System.out.println(message);
              // ous.write(("HTTP/1.1 200 OK\r\n\r\n" + message + "\n").getBytes());
              ous.write(("HTTP/1.1 200 OK\r\n\r\n" + "<h1>Hello Spankie</h1>" + "\n").getBytes());
              ous.flush();
              ous.close();
              quit = true;
            }
          } else {
            System.err.println("Connection to client is closed!");
            quit = true;
          }
        }
      }
    } catch (IOException e) {

    }
  }
}