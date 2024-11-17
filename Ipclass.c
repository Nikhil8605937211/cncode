#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void findClassAndType(char* ipAddress) {
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
    char ipClass;
    
    // Determine the IP class
    if (firstOctet >= 1 && firstOctet <= 127) {
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
    int isPrivate = 0;
    if ((ipClass == 'A' && firstOctet == 10) ||
        (ipClass == 'B' && firstOctet == 172 && octets[1] >= 16 && octets[1] <= 31) ||
        (ipClass == 'C' && firstOctet == 192 && octets[1] == 168)) {
        isPrivate = 1;
    }

    // Print the results
    printf("IP Address: %s\n", ipAddress);
    printf("Class: %c\n", ipClass);
    printf("Type: %s\n", isPrivate ? "Private" : "Public");
}

int main() {
    char ipAddress[16];
    printf("Enter an IP address: ");
    scanf("%15s", ipAddress);

    findClassAndType(ipAddress);

    return 0;
}
