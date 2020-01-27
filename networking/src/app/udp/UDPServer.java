package app.udp;

import java.io.*;
import java.net.*;

class UDPServer {
  public static void main(String args[]) throws Exception {
    try {
      DatagramSocket socket = new DatagramSocket(9090);
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];
      while (true) {
        // receive datagram packets from udp connections
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);

        String sentence = new String(receivePacket.getData());
        System.out.println("RECEIVED: " + sentence);
        String stringData = "hello client!";
        sendData = stringData.getBytes();
        InetAddress clientIpAddress = receivePacket.getAddress();
        int clientPort = receivePacket.getPort();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);
        socket.send(sendPacket);
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }

  }
}