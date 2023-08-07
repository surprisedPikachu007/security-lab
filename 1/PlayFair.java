/*
 * ALGORITHM:
 * 1. To encrypt a message, one would break the message into digrams (groups of
    2 letters)
    2. For example, &quot;HelloWorld&quot; becomes &quot;HE LL OW OR LD&quot;.
    3. These digrams will be substituted using the key table.
    4. Since encryption requires pairs of letters, messages with an odd number of
    characters usually append an uncommon letter, such as &quot;X&quot;, to complete the
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
        char[][] digrams;
        if (n % 2 == 0) {
            digrams = new char[n / 2][2];
        } else {
            digrams = new char[n / 2 + 1][2];
        }

        int j = 0;
        for (int i = 0; i < n; i += 2, j++) {
            digrams[j][0] = s.charAt(i);
            if (i + 1 < n) {
                digrams[j][1] = s.charAt(i + 1);
            } else {
                digrams[j][1] = 'X';
            }
        }

        char[][] table = createTable(k);

        for (char[] digram : digrams) {
            int[] pos1 = findPosition(table, digram[0]);
            int[] pos2 = findPosition(table, digram[1]);

            if (pos1[0] == pos2[0]) {
                res.append(table[pos1[0]][(pos1[1] + 1) % 5]);
                res.append(table[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) {
                res.append(table[(pos1[0] + 1) % 5][pos1[1]]);
                res.append(table[(pos2[0] + 1) % 5][pos2[1]]);
            } else {
                res.append(table[pos1[0]][pos2[1]]);
                res.append(table[pos2[0]][pos1[1]]);
            }
        }

        return res.toString();
    }

    static int[] findPosition(char[][] table, char c) {
        int[] pos = new int[2];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < table[i].length; j++) {
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

        String key = "SKY";

        String plainText = "BLUEBIRD";
        System.out.println("Plain Text: " + plainText);
        System.out.println("Encrypted Text: " + encrypt(plainText, key));
    }
}