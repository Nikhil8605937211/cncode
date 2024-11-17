// client.java
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) throws IOException {
        String serverAddress = "localhost"; // Server address
        int serverPort = 9876; // Server port

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Send message to server
            String message = "Hello, server!";
            writer.println(message);
            System.out.println("Sent to server: " + message);

            // Close resources
            writer.close();
        }
    }
}
