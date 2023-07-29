public class HillCipher {
    static int[] getCode(String s) {
        int n = s.length();
        int[] matrix = new int[n];

        for (int i = 0; i < n; i++) {
            matrix[i] = s.charAt(i) - 'A';
        }

        return matrix;
    }

    static int[][] getKey(String s, String k) {
        int n = s.length();
        int[][] matrix = new int[n][n];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = k.charAt(idx++) - 'A';
            }
        }

        return matrix;
    }

    static int[] multiply(int[][] a, int[] b) {
        int n = a.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int k = 0; k < n; k++) {
                sum += a[i][k] * b[k];
            }
            result[i] = sum % 26;
        }

        return result;
    }

    static String toString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            sb.append((char) (a[i] + 'A'));
        }

        return sb.toString();
    }

    static String encrypt(String s, String k) {
        int[] code = getCode(s);
        int[][] key = getKey(s, k);
        int[] result = multiply(key, code);

        return toString(result);
    }

    static String decrypt(String s, String k, int[][] inv) {
        int[] code = getCode(s);
        int[] result = multiply(inv, code);

        return toString(result);
    }

    public static void main(String[] args) {
        int[][] inv = {
                { 8, 5, 10 },
                { 21, 8, 21 },
                { 21, 12, 8 }
        };
        System.out.println(encrypt("ACT", "GYBNQKURP"));
        System.out.println(decrypt("POH", "GYBNQKURP", inv));
    }
}
