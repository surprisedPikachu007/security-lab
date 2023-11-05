/*
 * ALGORITHM:
 * 1. To encrypt a message, one would break the message into digrams (groups of
    2 letters)
    2. For example, "HelloWorld" becomes "HE LL OW OR LD".
    3. These digrams will be substituted using the key table.
    4. Since encryption requires pairs of letters, messages with an odd number of
    characters usually append an uncommon letter, such as "X", to complete the
    final digram.
    5. The two letters of the digram are considered opposite corners of a rectangle
    in the key table. To perform the substitution, apply the following 4 rules, in
    order, to each pair of letters in the plaintext:

    a. if letters appear on the same row of table, replace them with letter to immediate
    right (circular)
    b. if letters appear on the same column of table, replace them with letter immediately
    below (circular)
    c. if letters are not on same row or column, replace them with letters on same row
    but in the column of the other letter of the pair

 */

public class PlayFair {
    static String encrypt(String s, String k) {
        StringBuilder res = new StringBuilder();

        int n = s.length();
        char[][] digrams = new char[n][2];

        int i = 0, j = 0;
        while (i < n) {
            digrams[j][0] = s.charAt(i);
            i++;
            if (i < n && s.charAt(i) != digrams[j][0]) {
                digrams[j][1] = s.charAt(i);
                i++;
            } else {
                digrams[j][1] = 'X';
            }
            j++;
        }

        char[][] table = createTable(k);

        for (char[] digram : digrams) {
            if (digram[0] == digram[1]) {
                break;
            }

            int[] pos1 = findPosition(table, digram[0]);
            int[] pos2 = findPosition(table, digram[1]);
            int i1 = pos1[0], j1 = pos1[1], i2 = pos2[0], j2 = pos2[1];

            if (i1 == i2) { // same row
                // circular next element to the right
                res.append(table[i1][(j1 + 1) % 5]);
                res.append(table[i2][(j2 + 1) % 5]);
            } else if (j1 == j2) { // same column
                // circular next element below
                res.append(table[(i1 + 1) % 5][j1]);
                res.append(table[(i2 + 1) % 5][j2]);
            } else { // different row and column
                // same row but in the column of the other letter of the pair
                res.append(table[i1][j2]);
                res.append(table[i2][j1]);
            }
        }

        return res.toString();
    }

    static String decrypt(String s, String k) {
        StringBuilder res = new StringBuilder();

        int n = s.length();
        char[][] digrams = new char[n][2];

        int i = 0, j = 0;
        while (i < n) {
            digrams[j][0] = s.charAt(i);
            i++;
            if (i < n && s.charAt(i) != digrams[j][0]) {
                digrams[j][1] = s.charAt(i);
                i++;
            } else {
                digrams[j][1] = 'X';
            }
            j++;
        }

        char[][] table = createTable(k);

        for (char[] digram : digrams) {
            if (digram[0] == digram[1]) {
                break;
            }

            int[] pos1 = findPosition(table, digram[0]);
            int[] pos2 = findPosition(table, digram[1]);
            int i1 = pos1[0], j1 = pos1[1], i2 = pos2[0], j2 = pos2[1];

            if (i1 == i2) { // same row
                // circular previous element to the left
                res.append(table[i1][(j1 + 4) % 5]);
                res.append(table[i2][(j2 + 4) % 5]);
            } else if (j1 == j2) { // same column
                // circular previous element above
                res.append(table[(i1 + 4) % 5][j1]);
                res.append(table[(i2 + 4) % 5][j2]);
            } else { // different row and column
                // same row but in the column of the other letter of the pair
                res.append(table[i1][j2]);
                res.append(table[i2][j1]);
            }
        }

        return res.toString();
    }

    static int[] findPosition(char[][] table, char c) {
        int[] pos = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (table[i][j] == c) {
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }

        return pos;
    }

    static char[][] createTable(String k) {
        char[][] table = new char[5][5];
        boolean[] used = new boolean[26];

        int i = 0, j = 0;
        for (char c : k.toCharArray()) {
            if (!used[c - 'A']) {
                table[i][j] = c;
                used[c - 'A'] = true;
                j++;
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') {
                continue;
            }
            if (!used[c - 'A']) {
                table[i][j] = c;
                used[c - 'A'] = true;
                j++;
                if (j == 5) {
                    i++;
                    j = 0;
                }
            }
        }

        return table;
    }

    public static void main(String[] args) {
        System.out.println("PlayFair Cipher\n");

        String key = "MONARCHY";

        String plainText = "INSTRUMENTS";
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted Text: " + encrypt(plainText, key));

        String cipherText = "GATLMZCLRQXA";
        System.out.println("\nCipher Text: " + cipherText);
        System.out.println("Decrypted Text: " + decrypt(cipherText, key));
    }
}