import java.util.Scanner;

public class HammingCode {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Input 4-bit data
        System.out.print("Enter 4-bit data (as 4 digits): ");
        String data = sc.next();
        
        // Convert input string to integer array for easier processing
        int[] dataBits = new int[4];
        for (int i = 0; i < 4; i++) {
            dataBits[i] = Character.getNumericValue(data.charAt(i));
        }
        
        // Create an array for the 7-bit Hamming code
        int[] hammingCode = new int[7];
        
        // Assign data bits to the positions in Hamming code
        hammingCode[2] = dataBits[0]; // D1
        hammingCode[4] = dataBits[1]; // D2
        hammingCode[5] = dataBits[2]; // D3
        hammingCode[6] = dataBits[3]; // D4
        
        // Compute parity bits
        // P1 checks bits 1, 3, 5, 7 (D1, D2, D4)
        hammingCode[0] = hammingCode[2] ^ hammingCode[4] ^ hammingCode[6];
        
        // P2 checks bits 2, 3, 6, 7 (D1, D3, D4)
        hammingCode[1] = hammingCode[2] ^ hammingCode[5] ^ hammingCode[6];
        
        // P4 checks bits 4, 5, 6, 7 (D2, D3, D4)
        hammingCode[3] = hammingCode[4] ^ hammingCode[5] ^ hammingCode[6];
        
        // Print the Hamming code
        System.out.print("Hamming Code: ");
        for (int bit : hammingCode) {
            System.out.print(bit);
        }
        System.out.println();
        
        // Simulate received code with an error (introduce error in the code)
        // Flip the bit at position 5 (for example)
        int[] receivedCode = hammingCode.clone(); // Make a copy of the original code
        receivedCode[4] = receivedCode[4] == 0 ? 1 : 0; // Flip bit at position 5 (index 4)
        
        // Print the received code with the error
        System.out.println("Received code with error: ");
        for (int bit : receivedCode) {
            System.out.print(bit);
        }
        System.out.println(); // New line for readability
        
        // Detect error using parity check
        int[] check = new int[3];
        check[0] = receivedCode[0] ^ receivedCode[2] ^ receivedCode[4] ^ receivedCode[6]; // P1 check
        check[1] = receivedCode[1] ^ receivedCode[2] ^ receivedCode[5] ^ receivedCode[6]; // P2 check
        check[2] = receivedCode[3] ^ receivedCode[4] ^ receivedCode[5] ^ receivedCode[6]; // P4 check
        
        // Calculate error position
        int errorPos = check[0] * 1 + check[1] * 2 + check[2] * 4;
        
        if (errorPos != 0) {
            System.out.println("Error detected at position: " + errorPos);
            // Correct the error by flipping the erroneous bit
            receivedCode[errorPos - 1] = (receivedCode[errorPos - 1] == 0) ? 1 : 0;
            System.out.print("Error corrected. Corrected code: ");
            // Print corrected code
            for (int bit : receivedCode) {
                System.out.print(bit);
            }
        } else {
            System.out.println("No error detected.");
        }
        
        sc.close();
    }
}
