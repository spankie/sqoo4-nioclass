Check and sanitize source code

# NETWORKING IN JAVA

NETWORK is group of computers connected together.

# TCP/IP Network layers

- Application (http or ftp or smtp)
- Transport (TCP or UDP; add headers and port which specifies/identifies the process)
- Internet (IP which identifies the destination and source network using ip addresses)
- Network Interface

### TCP/IP provides two transport layer protocols

- TCP (Transmision control protocol)
- UDP (user datagram protocol)

TCP keeps track of what has been sent and what has not been sent by transmission and acknowledgement

UDP packets may be sent out of order. And some data may be lost. It is unreliable but fast.

## java socket

socket programming is used for communication between applications running on different runtime environments. It can be either connection oriented or connection-less

A socket is one endpoint of a two way communication link between two programs running on the network. The socket is bound to a port number that the TCP layer can identify the application that data is destined to be sent.

A port is a 16bit unsigned integer. _Max 65535_

### Few methods in a socket

- public InputStream getInputStream()
- public OutputStream getOutputStream()
- public synchronized void close()

### few methods of socketServer

- public Socket accept()
- public synchronized void close()

## Examples:

- single (You can test the server using telnet on windows or nc on macos. Test with browser to see how browsers connects to a server)
- portscanner
- inet

UDP id a connectionless protocol, with no connection between the endpoints in the network.
