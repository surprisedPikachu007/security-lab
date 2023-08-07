public class VigenereCipher {
    static String encrypt(String s, String k) {
        StringBuffer res = new StringBuffer();

        int j = 0;
        for (int i = 0; i < s.length(); i++, j = (j + 1) % k.length()) {
            res.append((char) ((s.charAt(i) + k.charAt(j)) % 26 + 'A'));
        }

        return res.toString();
    }

    static String decrypt(String s, String k) {
        StringBuffer res = new StringBuffer();

        int j = 0;
        for (int i = 0; i < s.length(); i++, j = (j + 1) % k.length()) {
            res.append((char) ((s.charAt(i) - k.charAt(j) + 26) % 26 + 'A'));
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println("Vigenere Cipher\n");

        String key = "WIMBLEDON";

        String plainText = "CSK";
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted Text: " + encrypt(plainText, key) + "\n");

        String cipherText = "YAW";
        System.out.println("Cipher Text: " + cipherText);
        System.out.println("Decrypted Text: " + decrypt(cipherText, key));
    }
}
