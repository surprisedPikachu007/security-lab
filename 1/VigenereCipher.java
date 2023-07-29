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
        System.out.println(encrypt("GEEKSFORGEEKS", "AYUSH"));
        System.out.println(decrypt("GCYCZFMLYLEIM", "AYUSH"));
    }
}
