package app.single;

import java.io.BufferedReader;
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

  public static void main(String[] args) {
    boolean quit = false;
    final int port = 8081;
    System.out.printf("Server is starting on port: %s\n", port);
    try (ServerSocket ss = new ServerSocket(port)) {
      // we can use the server socket here...
      Socket client = ss.accept();
      System.out.printf("New user connected: %s:%d\n", client.getInetAddress().getHostAddress(), client.getPort());
      InputStream is = client.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      OutputStream ous = client.getOutputStream();
      while (!quit) {
        String message = br.readLine();
        System.out.printf("Client: %s\n", message);

        if (message.equals("quit")) {
          // message = "Thanks for chatting with me...";
          quit = true;
        } else {
          if (!client.isClosed()) {
            // "HTTP/1.1 200 OK\r\n\r\n" +
            ous.write(("HTTP/1.1 200 OK\r\n\r\n" + message).getBytes());
          } else {
            System.err.println("Connection to client is closed!");
            quit = true;
          }
        }
      }
    } catch (IOException e) {
      System.err.printf("%s - %s\n", e.getClass().getSimpleName(), e.getMessage());
    }
  }
}