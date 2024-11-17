#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <string.h>

void findSubnetMask(char* ipAddress, int numberOfSubnets) {
    int octets[4];
    char* token = strtok(ipAddress, ".");
    
    // Parse the IP address into octets
    for (int i = 0; i < 4; i++) {
        if (token == NULL) {
            printf("Invalid IP address format.\n");
            return;
        }
        octets[i] = atoi(token);
        token = strtok(NULL, ".");
    }

    int firstOctet = octets[0];
    int defaultSubnetBits;

    // Determine the default subnet mask based on the IP class
    if (firstOctet >= 1 && firstOctet <= 127) { // Class A
        defaultSubnetBits = 8;
    } else if (firstOctet >= 128 && firstOctet <= 191) { // Class B
        defaultSubnetBits = 16;
    } else if (firstOctet >= 192 && firstOctet <= 223) { // Class C
        defaultSubnetBits = 24;
    } else {
        printf("Invalid IP address for subnetting.\n");
        return;
    }

    // Calculate the total number of bits needed for subnetting
    int totalSubnetBits = (int) ceil(log(numberOfSubnets) / log(2));
    int totalBits = defaultSubnetBits + totalSubnetBits;

    // Check if the total number of bits is valid
    if (totalBits > 32) {
        printf("Too many subnets. Cannot be accommodated in a 32-bit IP address.\n");
        return;
    }

    // Calculate the subnet mask
    unsigned int subnetMask = 0xFFFFFFFF << (32 - totalBits);

    // Print the subnet mask in dotted decimal format
    printf("IP Address: %s\n", ipAddress);
    printf("Calculated Subnet Mask: %d.%d.%d.%d\n",
           (subnetMask >> 24) & 0xFF,
           (subnetMask >> 16) & 0xFF,
           (subnetMask >> 8) & 0xFF,
           subnetMask & 0xFF);
    printf("Number of Subnets: %d\n", numberOfSubnets);
}

int main() {
    char ipAddress[16];
    int numberOfSubnets;

    // Get the IP address and number of subnets from the user
    printf("Enter an IP address (e.g., 192.168.1.0): ");
    scanf("%15s", ipAddress);

    printf("Enter the number of subnets: ");
    scanf("%d", &numberOfSubnets);

    // Call the function to calculate the subnet mask
    findSubnetMask(ipAddress, numberOfSubnets);

    return 0;
}
