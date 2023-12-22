import java.math.BigInteger;
import java.util.Scanner;

public class Affine_java {

    public static String encrypt(String message, BigInteger a, BigInteger b) {
        StringBuilder encrypted = new StringBuilder();

        // Loop through each character in the message
        for (int i = 0; i < message.length(); i++) {
            char currentChar = message.charAt(i);

             // Check if the character is a letter
            if (Character.isLetter(currentChar)) {
                // Determine the base ('A' for uppercase, 'a' for lowercase)
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';

                // Calculate the index of the character in the alphabet
                int charIndex = currentChar - base;

                // Convert charIndex to a BigInteger and apply the Affine cipher formula for encryption
                BigInteger charValue = BigInteger.valueOf(charIndex);
                BigInteger encryptedIndex = a.multiply(charValue).add(b).mod(BigInteger.valueOf(26));

                // Convert the encrypted index back to a character and append to the result
                char encryptedChar = (char) (encryptedIndex.intValue() + base);
                encrypted.append(encryptedChar);
            } 
            
            else {
                // Non-alphabetic characters are appended as is
                encrypted.append(currentChar);
            }
        }
        return encrypted.toString();
    }

    public static String decrypt(String cipher, BigInteger a, BigInteger b) {
        StringBuilder decrypted = new StringBuilder();
        BigInteger inverseA = BigInteger.ZERO;
        int flag = 0;

        // Find the inverse of a
        for (int i = 0; i < 26; i++) {
            flag = (a.intValue() * i) % 26;
            // If (a * i) % 26 == 1 then i is a multiplicative inverse of a
            if (flag == 1) {
                inverseA = BigInteger.valueOf(i);
            }
        }

        // Decrypt
        for (int i = 0; i < cipher.length(); i++) {
            char currentChar = cipher.charAt(i);

            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                int charIndex = currentChar - base;
                BigInteger charValue = BigInteger.valueOf(charIndex);
                BigInteger decryptedIndex = inverseA.multiply(charValue.subtract(b).add(BigInteger.valueOf(26)))
                        .mod(BigInteger.valueOf(26));
                char decryptedChar = (char) (decryptedIndex.intValue() + base);
                decrypted.append(decryptedChar);
            } 
            
            else {
                // Non-alphabetic characters are appended as is
                decrypted.append(currentChar);
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

