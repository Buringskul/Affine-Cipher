import java.math.BigInteger;
import java.util.Scanner;

public class Affine_java {

    public static String encrypt(String message, BigInteger a, BigInteger b) {
        String base = message.toUpperCase();
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < base.length(); i++) {
            if (Character.isLetter(base.charAt(i))) {
                BigInteger charValue = BigInteger.valueOf(base.charAt(i) - 'A');
                BigInteger encryptedValue = (a.multiply(charValue).add(b)).mod(BigInteger.valueOf(26));
                encrypted.append((char) (encryptedValue.intValue() + 'A'));
            } else {
                encrypted.append(base.charAt(i));
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String cipher, BigInteger a, BigInteger b) {
        StringBuilder decrypted = new StringBuilder();
        BigInteger inverseA = a.modInverse(BigInteger.valueOf(26));

        for (int i = 0; i < cipher.length(); i++) {
            if (Character.isLetter(cipher.charAt(i))) {
                BigInteger charValue = BigInteger.valueOf(cipher.charAt(i) - 'A');
                BigInteger decryptedValue = (inverseA.multiply(charValue.subtract(b))).mod(BigInteger.valueOf(26));
                int intValue = decryptedValue.intValue();
                if (intValue < 0) intValue += 26; // Handling negative values
                decrypted.append((char) (intValue + 'A'));
            } else {
                decrypted.append(cipher.charAt(i));
            }
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); 
        String msg = input.nextLine();
        BigInteger a = input.nextBigInteger();
        BigInteger b = input.nextBigInteger();
        
        System.out.println("Encrypted message is: " + encrypt(msg, a, b));
        
        String encrypted = encrypt(msg, a, b);
        
        System.out.println("Decrypted message is: " + decrypt(encrypted, a, b));
    }
}

