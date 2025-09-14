import java.math.BigInteger;
import java.util.Scanner;

class RSA {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input message
        System.out.print("Enter the message (as integer): ");
        BigInteger msg = BigInteger.valueOf(sc.nextInt());

        // Input primes
        System.out.print("Enter the first prime number: ");
        BigInteger p = BigInteger.valueOf(sc.nextInt());

        System.out.print("Enter the second prime number: ");
        BigInteger q = BigInteger.valueOf(sc.nextInt());

        // Compute n and z
        BigInteger n = p.multiply(q);
        BigInteger z = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        System.out.println("The value of z = " + z);

        // Find e (public exponent)
        BigInteger e = BigInteger.TWO;
        while (e.compareTo(z) < 0) {
            if (e.gcd(z).equals(BigInteger.ONE)) {
                break;
            }
            e = e.add(BigInteger.ONE);
        }
        System.out.println("The value of e = " + e);

        // Find d (private exponent) using modInverse
        BigInteger d = e.modInverse(z);
        System.out.println("The value of d = " + d);

        // Encrypt: c = msg^e mod n
        BigInteger c = msg.modPow(e, n);
        System.out.println("Encrypted message is : " + c);

        // Decrypt: msgback = c^d mod n
        BigInteger msgback = c.modPow(d, n);
        System.out.println("Decrypted message is : " + msgback);

        sc.close();
    }
}
