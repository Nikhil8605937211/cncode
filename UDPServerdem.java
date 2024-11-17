import java.net.*;
import java.io.*;

public class UDPServerdem {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileInputStream fis = null;
        try {
            socket = new DatagramSocket(9876);
            File file = new File("sample.txt");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            InetAddress clientAddress = InetAddress.getByName("localhost");
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                DatagramPacket packet = new DatagramPacket(buffer, bytesRead, clientAddress, 9877);
                socket.send(packet);
            }

            // Send an empty packet to signal the end of the file transmission
            DatagramPacket endPacket = new DatagramPacket(new byte[0], 0, clientAddress, 9877);
            socket.send(endPacket);

            System.out.println("File sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}





// import java.net.*;
// import java.io.*;

// public class UDPclient {

//     public static void main(String[] args) {
//         DatagramSocket socket = null;
//         FileOutputStream fos = null;
//         try {
//             socket = new DatagramSocket(9877);
//             byte[] buffer = new byte[1024];
//             fos = new FileOutputStream("received_sample.txt");
//             DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

//             while (true) {
//                 socket.receive(packet);
//                 if (packet.getLength() == 0) break; // Check for end of file transmission
//                 fos.write(packet.getData(), 0, packet.getLength());
//             }

//             System.out.println("File received successfully.");
//         } catch (IOException e) {
//             e.printStackTrace();
//         } finally {
//             if (fos != null) {
//                 try {
//                     fos.close();
//                 } catch (IOException e) {
//                     e.printStackTrace();
//                 }
//             }
//             if (socket != null && !socket.isClosed()) {
//                 socket.close();
//             }
//         }
//     }
// }

