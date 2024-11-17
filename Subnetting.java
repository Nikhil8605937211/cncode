import java.util.Scanner;

public class Subnetting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter an IP address (e.g., 192.168.1.0): ");
        String ipAddress = scanner.nextLine();

        System.out.print("Enter the number of subnets: ");
        int numberOfSubnets = scanner.nextInt();

        findSubnetMask(ipAddress, numberOfSubnets);
        scanner.close();
    }

    public static void findSubnetMask(String ipAddress, int numberOfSubnets) {
        // Split the IP address into octets
        String[] octets = ipAddress.split("\\.");
        int firstOctet = Integer.parseInt(octets[0]);

        // Determine the default subnet mask based on the IP class
        int defaultSubnetBits;
        if (firstOctet >= 1 && firstOctet <= 127) { // Class A
            defaultSubnetBits = 8;
        } else if (firstOctet >= 128 && firstOctet <= 191) { // Class B
            defaultSubnetBits = 16;
        } else if (firstOctet >= 192 && firstOctet <= 223) { // Class C
            defaultSubnetBits = 24;
        } else {
            System.out.println("Invalid IP address for subnetting.");
            return;
        }

        // Calculate the total number of bits needed for subnetting
        int totalSubnetBits = (int) Math.ceil(Math.log(numberOfSubnets) / Math.log(2));
        int totalBits = defaultSubnetBits + totalSubnetBits;

        // Check if the total number of bits is valid
        if (totalBits > 32) {
            System.out.println("Too many subnets. Cannot be accommodated in a 32-bit IP address.");
            return;
        }

        // Calculate the subnet mask
        int subnetMask = 0xFFFFFFFF << (32 - totalBits);

        // Print the subnet mask in dotted decimal format
        String subnetMaskStr = String.format("%d.%d.%d.%d",
                (subnetMask >> 24) & 0xFF,
                (subnetMask >> 16) & 0xFF,
                (subnetMask >> 8) & 0xFF,
                subnetMask & 0xFF);

        System.out.println("IP Address: " + ipAddress);
    
        System.out.println("Calculated Subnet Mask: " + subnetMaskStr);
        System.out.println("Number of Subnets: " + numberOfSubnets);
    }

  
}
