
import java.net.*;
import java.io.*;

public class UDPclient {

    public static void main(String[] args) {
        DatagramSocket socket = null;
        FileOutputStream fos = null;
        try {
            socket = new DatagramSocket(9877);
            byte[] buffer = new byte[1024];
            fos = new FileOutputStream("received_sample.txt");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                if (packet.getLength() == 0) break; // Check for end of file transmission
                fos.write(packet.getData(), 0, packet.getLength());
            }

            System.out.println("File received successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
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
