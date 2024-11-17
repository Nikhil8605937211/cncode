import java.util.Scanner;

public class IpClass {

    public static void findClassAndType(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        int firstOctet = Integer.parseInt(octets[0]);

        // Determine the IP class
        char ipClass;
        if (firstOctet >= 1 && firstOctet <= 127) { // Corrected range to start from 1 instead of 0
            ipClass = 'A';
        } else if (firstOctet >= 128 && firstOctet <= 191) {
            ipClass = 'B';
        } else if (firstOctet >= 192 && firstOctet <= 223) {
            ipClass = 'C';
        } else if (firstOctet >= 224 && firstOctet <= 239) {
            ipClass = 'D';
        } else {
            ipClass = 'E';
        }

        // Check if the IP is private
        boolean isPrivate = false;
        if ((ipClass == 'A' && firstOctet == 10) ||
            (ipClass == 'B' && firstOctet == 172 && Integer.parseInt(octets[1]) >= 16 && Integer.parseInt(octets[1]) <= 31) ||
            (ipClass == 'C' && firstOctet == 192 && Integer.parseInt(octets[1]) == 168)) {
            isPrivate = true;
        }

        // Print the results
        System.out.println("IP Address: " + ipAddress);
        System.out.println("Class: " + ipClass);
        System.out.println("Type: " + (isPrivate ? "Private" : "Public"));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an IP address: ");
        String ipAddress = scanner.nextLine();

        findClassAndType(ipAddress);
        scanner.close();
    }
}
