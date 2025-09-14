import java.util.Scanner;

public class CRC {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input Message
        System.out.print("Enter the number of bits in the message: ");
        int messageLength = sc.nextInt();
        int[] message = new int[messageLength];
        System.out.println("Enter message bits (space-separated 0s and 1s): ");
        for (int i = 0; i < messageLength; i++) {
            message[i] = sc.nextInt();
        }

        // Input Generator
        System.out.print("Enter the number of bits in the generator: ");
        int generatorLength = sc.nextInt();
        int[] generator = new int[generatorLength];
        System.out.println("Enter generator bits (space-separated 0s and 1s): ");
        for (int i = 0; i < generatorLength; i++) {
            generator[i] = sc.nextInt();
        }

        // Prepare data array (message + zeros for CRC calculation)
        int[] data = new int[messageLength + generatorLength - 1];
        System.arraycopy(message, 0, data, 0, messageLength);

        // Copy generator
        int[] divisor = new int[generatorLength];
        System.arraycopy(generator, 0, divisor, 0, generatorLength);

        // Perform Division (XOR)
        for (int i = 0; i < messageLength; i++) {
            if (data[i] == 1) {
                for (int j = 0; j < divisor.length; j++) {
                    data[i + j] ^= divisor[j];
                }
            }
        }

        // CRC = last (generatorLength-1) bits
        int[] crc = new int[generatorLength - 1];
        System.arraycopy(data, messageLength, crc, 0, generatorLength - 1);

        // Final transmitted codeword = message + CRC
        int[] codeword = new int[messageLength + crc.length];
        System.arraycopy(message, 0, codeword, 0, messageLength);
        System.arraycopy(crc, 0, codeword, messageLength, crc.length);

        // Display transmitted codeword
        System.out.print("Transmitted Codeword (Message + CRC): ");
        for (int bit : codeword) {
            System.out.print(bit);
        }
        System.out.println();

        // --- Receiver Side Verification ---
        System.out.print("Enter the received codeword length: ");
        int receivedLength = sc.nextInt();
        int[] received = new int[receivedLength];
        System.out.println("Enter received codeword bits (space-separated 0s and 1s): ");
        for (int i = 0; i < receivedLength; i++) {
            received[i] = sc.nextInt();
        }

        // Perform Division on received codeword
        int[] remainder = received.clone();
        for (int i = 0; i < messageLength; i++) {
            if (remainder[i] == 1) {
                for (int j = 0; j < divisor.length; j++) {
                    remainder[i + j] ^= divisor[j];
                }
            }
        }

        // Check validity (all remainder bits should be 0)
        boolean valid = true;
        for (int i = messageLength; i < remainder.length; i++) {
            if (remainder[i] != 0) {
                valid = false;
                break;
            }
        }

        if (valid) {
            System.out.println("Data stream is VALID (no error).");
        } else {
            System.out.println("Data stream is INVALID (CRC error occurred).");
        }

        sc.close();
    }
}
