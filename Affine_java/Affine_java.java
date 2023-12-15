import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Affine_java {
    // Keys
    static int a = 5;
    static int b = 8;

    static String encrypt(String message) {
        String base = message.toUpperCase();
        String encrypted = "";
        for (int i = 0; i < base.length(); i++) {
            // Avoid encrypting spaces
            if (base.charAt(i) != ' ') {
                // (ax + b) % 26
                encrypted += (char)((((a * base.charAt(i)) + b) % 26) + 65);
            }
            // Adds space to encryption
            else {
                encrypted += base.charAt(i);
            }
        }
        return encrypted;
    }

    static String decrypt(String cipher) {
        String base = cipher.toUpperCase(); 
        String decrypted = "";
        int inverse_a = 0;
        int flag = 0;

        // Find the inverse of a
        for (int i = 0; i < 26; i++) {
            flag = (a * i) % 26;
            // If (a * i) % 26 == 1 then i is a multiplicative inverse of a
            if (flag == 1) {
                inverse_a = i;
            }
        }

        // Decrypt
        for (int i = 0; i < base.length(); i++) {
            if (base.charAt(i) != ' ') {
                // a^-1 (x - b) % 26
                decrypted += (char)(((inverse_a * ((base.charAt(i) - b)) % 26)) + 65);
            }
            else {
                decrypted += base.charAt(i);
            }
        }

        return decrypted;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in); // subbing this out for input from front end - kw
        String msg = input.nextLine();
        
        System.out.println("Encrypted message is: " + encrypt(msg));
        System.out.println("Decrypted message is: " + decrypt(encrypt(msg)));
    }
}

