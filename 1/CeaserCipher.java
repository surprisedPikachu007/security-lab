public class CeaserCipher {
    static String encrypt(String s, int k) {
        String res = "";

        for (char c : s.toCharArray()) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            res += ((char) ((c - base + k) % 26 + base));
        }

        return res;
    }

    static String decrypt(String s, int k) {
        return encrypt(s, 26 - k);
    }

    public static void main(String[] args) {
        String plainText = "abC";
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encryption: " + encrypt("abC", 3));
        System.out.println("Decryption: " + decrypt("deF", 3));
    }
}