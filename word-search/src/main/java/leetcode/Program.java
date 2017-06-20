package leetcode;

public final class Program {
    public static void main(String[] args) {
        System.out.println(
                new Solution().exist(
                        new char[][]{
                                {'A', 'B', 'C', 'E'},
                                {'S', 'F', 'C', 'S'},
                                {'A', 'D', 'E', 'E'}
                        },
                        "ABCCED"));
    }

    static final class Solution {
        boolean exist(char[][] board, String word) {
            if ("".equals(word)) {
                return true;
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (exist(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean exist(char[][] board, String word, int i, int j, int p) {
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
                return false;
            }

            if (board[i][j] != word.charAt(p)) {
                return false;
            }

            p++;
            if (p >= word.length()) {
                return true;
            }

            board[i][j] = '\0';
            boolean result = exist(board, word, i - 1, j, p) ||
                    exist(board, word, i + 1, j, p) ||
                    exist(board, word, i, j - 1, p) ||
                    exist(board, word, i, j + 1, p);
            board[i][j] = word.charAt(p - 1);

            return result;
        }
    }
}
