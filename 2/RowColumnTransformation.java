public class RowColumnTransformation {
    static String encrypt(String s) {
        s = s.replaceAll("\\s", "");

        int len = s.length();
        int row = (int) Math.sqrt(len);
        int col = (int) Math.ceil((double) len / row);

        char[][] matrix = new char[row][col];
        int index = 0;
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                if (index < len) {
                    matrix[i][j] = s.charAt(index++);
                } else {
                    matrix[i][j] = ' ';
                }
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                res.append(matrix[i][j]);
            }
        }

        return res.toString();
    }

    private static String decrypt(String s) {
        int len = s.length();
        int row = (int) Math.sqrt(len);
        int col = (int) Math.ceil((double) len / row);

        char[][] matrix = new char[row][col];
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col && index < len; j++) {
                matrix[i][j] = s.charAt(index++);
            }
        }

        StringBuilder res = new StringBuilder();
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row && matrix[i][j] != ' '; i++) {
                res.append(matrix[i][j]);
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("Periplaneta americana"));
        System.out.println(decrypt("Ppemceltearaarninaia"));
    }
}
