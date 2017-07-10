package leetcode;

import java.util.Arrays;

public final class ValidSudoku {
    public static void main(String[] args) {

    }

    public static final class Solution {
        private static boolean checkCell(char c, boolean[] digits) {
            if (c == '.') {
                return true;
            }
            int digit = c - '1';
            if (digits[digit]) {
                return false;
            }
            digits[digit] = true;
            return true;
        }

        public boolean isValidSudoku(char[][] board) {
            boolean[] digits = new boolean[9];

            for (int i = 0; i < board.length; i++) {
                Arrays.fill(digits, false);
                for (int j = 0; j < board.length; j++) {
                    if (!checkCell(board[i][j], digits)) {
                        return false;
                    }
                }
            }

            for (int j = 0; j < board.length; j++) {
                Arrays.fill(digits, false);
                for (int i = 0; i < board.length; i++) {
                    if (!checkCell(board[i][j], digits)) {
                        return false;
                    }
                }
            }

            for (int t = 0; t < board.length; t += 3) {
                for (int l = 0; l < board.length; l += 3) {
                    Arrays.fill(digits, false);
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            if (!checkCell(board[t + i][l + j], digits)) {
                                return false;
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
