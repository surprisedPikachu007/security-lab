public class CeaserCipher {
    static String ceaserCipher(String s, int k) {
        String res = "";

        for (char c : s.toCharArray()) {
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            res += ((char) ((c - base + k) % 26 + base));
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(ceaserCipher("abC", 3));
    }
}