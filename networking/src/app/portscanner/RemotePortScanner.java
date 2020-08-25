package app.portscanner;

// Import necessary packages
import java.io.*;
import java.net.*;

/**
 *
 * @author Spankie Dee
 */
public class RemotePortScanner {
  public static void main(String[] args) {
    // Create a Buffered reader that reads the user input
    InputStreamReader in = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(in);

     // store the targe ip address
    String targetIp = "";
    // store the starting port
    int fromPort = 0; 
     // store the ending port
    int toPort = 0;

    // ask user to enter the ip address
    System.out.print("Please enter the target ip address: "); 

    try {
       // read the target ip address
      targetIp = reader.readLine();
    }
     // error when trying to read user input
    catch (Exception e) {
      System.out.println("Cannot read the ip address! " + e.toString());
    }

    // boolean value which is used to control the validation process
    boolean isValid = false;

    // port 80 - 2000

    // get the first port to scan
    // as long as isValid is false, try to obtain a valid port number
    while (!isValid) {
      try {
         // ask user to type the first port
        System.out.print("Please enter the first port to start from: ");
         // read the port number
        String portString = reader.readLine();
         // try to convert string to integer
        fromPort = Integer.parseInt(portString);
         // verify that port is in the valid range
        if (fromPort > 0 && fromPort < 65536)
        {
           // set variable to true and exit the while loop
          isValid = true;
        } else {
           // inform user about the valid port range
          System.out.println("Invalid port! Port range is: 1 - 65535");
        }
      } catch (Exception e1) {
         // if the user does not enter a number
        System.out.println("Please insert a number!");
      }
    }

    // reinitialize the boolean value to false in order to start a new validation
    // process
    isValid = false;

    // Get the last port to scan
    // as long as isValid is false, try to obtain a valid port number
    while (!isValid) {
      try {
        // ask user to type the last port
        System.out.print("Please enter the last port to end the scan: ");
        // read the port number
        String portString = reader.readLine();
        // try to convert string to integer
        toPort = Integer.parseInt(portString);
        // verify that port is in the valid range
        if (toPort > 0 && toPort < 65536)
        {
          // verify if the toPort is greated than fromPort
          if (toPort >= fromPort)
          {
            // set the boolean value to true and exit the while loop
            isValid = true;
          }
        } else {
          // inform user about the valid port range
          System.out.println("Invalid port! Port range is: 0 - 65536");
        }
      } catch (Exception e) {
        // if the user does not enter a number
        System.out.println("Please insert a number!");
      }
    }

    // start with fromPort value
    int port = fromPort;
    // use the given port range
    while (port >= fromPort && port <= toPort)
    {
      try {
        // try to open on socket connection using the specified ip address
        // and the test port number
        Socket socket = new Socket(targetIp, port);
        // print listening port
        System.out.println("Port " + port + " is in listening state! (open)");
        socket.close();
      } catch (UnknownHostException e1){
        // catch block is executed if an invalid host was entered
        System.out.println("Unknown host exception " + e1.toString());
      } catch (IOException e2){
        // execute when the port is already opened

        // print what port is opened
        System.out.println("Port " + port + " is closed!");
      } catch (Exception e){
        // execute if an other exception is raised

        // print the error
        System.out.println(e.toString());
      }
      // increment port number in order to test the next port
      port++;
    }
  }
}
