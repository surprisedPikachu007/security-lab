import java.util.Arrays;

public class RailFenceCipher {

    static char[][] railBuilder(String s, int k) {
        char[][] rail = new char[k][s.length()];
        for (int i = 0; i < k; i++)
            Arrays.fill(rail[i], '\n');

        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < s.length(); i++) {
            if (row == 0 || row == k - 1)
                down = !down;
            rail[row][col++] = s.charAt(i);
            row += down ? 1 : -1;
        }

        return rail;
    }

    static String encrypt(String s, int k) {
        StringBuilder res = new StringBuilder();

        char[][] rail = railBuilder(s, k);

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (rail[i][j] != '\n')
                    res.append(rail[i][j]);
            }
        }

        return res.toString();
    }

    static String decrypt(String s, int k) {
        StringBuilder res = new StringBuilder();

        char[][] rail = railBuilder(s, k);

        int index = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < s.length(); j++) {
                if (rail[i][j] != '\n')
                    rail[i][j] = s.charAt(index++);
            }
        }

        boolean down = false;
        int row = 0, col = 0;

        for (int i = 0; i < s.length(); i++) {
            if (row == 0 || row == k - 1)
                down = !down;
            res.append(rail[row][col++]);
            row += down ? 1 : -1;
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println("Rail Fence Cipher\n");

        int key = 3;
        String plainText = "ThunderBird";
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted Text: " + encrypt(plainText, key) + "\n");

        String cipherText = "TdihneBrurd";
        System.out.println("Cipher Text: " + cipherText);
        System.out.println("Decrypted Text: " + decrypt(cipherText, key));
    }
}