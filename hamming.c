#include <stdio.h>
#include <string.h>

// Function to flip the bit (0 to 1 or 1 to 0)
int flipBit(int bit) {
    return bit == 0 ? 1 : 0;
}

int main() {
    char data[5];  // Array to store the 4-bit data (as 4 characters) plus null terminator
    int dataBits[4];  // Array to store the data bits
    int hammingCode[7];  // Array to store the 7-bit Hamming code
    int receivedCode[7];  // Array to store the received code with error
    int check[3];  // Array to store the parity checks
    int errorPos;  // Variable to store the error position

    // Input 4-bit data
    printf("Enter 4-bit data (as 4 digits): ");
    scanf("%4s", data);

    // Convert input string to integer array for easier processing
    for (int i = 0; i < 4; i++) {
        dataBits[i] = data[i] - '0';  // Convert char to int
    }

    // Assign data bits to the positions in Hamming code
    hammingCode[2] = dataBits[0];  // D1
    hammingCode[4] = dataBits[1];  // D2
    hammingCode[5] = dataBits[2];  // D3
    hammingCode[6] = dataBits[3];  // D4

    // Compute parity bits
    hammingCode[0] = hammingCode[2] ^ hammingCode[4] ^ hammingCode[6];  // P1
    hammingCode[1] = hammingCode[2] ^ hammingCode[5] ^ hammingCode[6];  // P2
    hammingCode[3] = hammingCode[4] ^ hammingCode[5] ^ hammingCode[6];  // P4

    // Print the Hamming code
    printf("Hamming Code: ");
    for (int i = 0; i < 7; i++) {
        printf("%d", hammingCode[i]);
    }
    printf("\n");

    // Simulate received code with an error (introduce error in the code)
    memcpy(receivedCode, hammingCode, sizeof(hammingCode));  // Copy the original code
    receivedCode[4] = flipBit(receivedCode[4]);  // Flip bit at position 5 (index 4)

    // Print the received code with the error
    printf("Received code with error: ");
    for (int i = 0; i < 7; i++) {
        printf("%d", receivedCode[i]);
    }
    printf("\n");

    // Detect error using parity check
    check[0] = receivedCode[0] ^ receivedCode[2] ^ receivedCode[4] ^ receivedCode[6];  // P1 check
    check[1] = receivedCode[1] ^ receivedCode[2] ^ receivedCode[5] ^ receivedCode[6];  // P2 check
    check[2] = receivedCode[3] ^ receivedCode[4] ^ receivedCode[5] ^ receivedCode[6];  // P4 check

    // Calculate error position
    errorPos = check[0] * 1 + check[1] * 2 + check[2] * 4;

    if (errorPos != 0) {
        printf("Error detected at position: %d\n", errorPos);
        // Correct the error by flipping the erroneous bit
        receivedCode[errorPos - 1] = flipBit(receivedCode[errorPos - 1]);
        printf("Error corrected. Corrected code: ");
        // Print corrected code
        for (int i = 0; i < 7; i++) {
            printf("%d", receivedCode[i]);
        }
    } else {
        printf("No error detected.");
    }

    printf("\n");
    return 0;
}
