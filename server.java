// SocketServer.java
import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        int port = 9876; // Port number for the server

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server is running and waiting for client requests...");

            while (true) {
                Socket socket = server.accept(); // Wait for incoming connections

                // Read from socket
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message = reader.readLine();
                System.out.println("Received from client: " + message);

                // Close resources
                reader.close();
                socket.close();
            }
        }
    }
}



// // client.java
// import java.io.*;
// import java.net.*;

// public class client {
//     public static void main(String[] args) throws IOException {
//         String serverAddress = "localhost"; // Server address
//         int serverPort = 9876; // Server port

//         try (Socket socket = new Socket(serverAddress, serverPort)) {
//             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

//             // Send message to server
//             String message = "Hello, server!";
//             writer.println(message);
//             System.out.println("Sent to server: " + message);

//             // Close resources
//             writer.close();
//         }
//     }
// }

