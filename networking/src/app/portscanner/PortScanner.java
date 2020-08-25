package app.portscanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * PortScanner of local ports
 */
public class PortScanner {

  public static void main(String[] args) {
    int port = 1;
    int maxPort = 65535;
    while (port < maxPort) {
      try (ServerSocket ss = new ServerSocket(port)) {
        /*
        // ServerSocket s = new ServerSocket(port);
        // System.out.printf("wainting for clients... on: %d\n", port);
        // boolean stop = false;
        // while(!stop)

        // Socket socket = ss.accept();
        // PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // out.println("HTTP/1.1 200 OK\r\n\r\n" + "Hello client!");
        // BufferedReader input = new BufferedReader(new
        // InputStreamReader(socket.getInputStream()));
        // String clientInput = input.readLine();
        // System.out.println(clientInput);
        // input.close();
        // out.close();
        // socket.close();
        // break;
        */
      } catch (IOException e) {
        // System.err.printf("%s - Port %d %s\n", e.getClass().getSimpleName(), port,
        // e.getMessage());
        System.err.printf("Port %d is in use (open)\n", port);
      }
      port++;
    }
  }
}