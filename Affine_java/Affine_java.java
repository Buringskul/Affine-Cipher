public class Affine_java {
    // Keys
    static int a = 5;
    static int b = 8;

    static String encrypt(String message) {
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            // Avoid encrypting spaces
            if (message.charAt(i) != ' ') {
                // (ax + b) % 26
                encrypted += (char)((((a * message.charAt(i)) + b) % 26) + 65);
            }
            // Adds space to encryption
            else {
                encrypted += message.charAt(i);
            }
        }
        return encrypted;
    }

    static String decrypt(String cipher) {
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
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != ' ') {
                // a^-1 (x - b) % 26
                decrypted += (char)(((inverse_a * ((cipher.charAt(i) - b)) % 26)) + 65);
            }
            else {
                decrypted += cipher.charAt(i);
            }
        }

        return decrypted;
    }

    public static void main(String[] args) {
        String msg = "HELLO WORLD";
        System.out.println("Encrypted message is: " + encrypt(msg));
        System.out.println("Decrypted message is: " + decrypt(encrypt(msg)));
    }
}

