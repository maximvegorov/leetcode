package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class NQueens {
    public static void main(String[] args) {
        new Solution().solveNQueens(4)
                .forEach(Solution::printSolution);
    }

    public static final class Solution {
        private static void printSolution(List<String> rows) {
            for (String row : rows) {
                System.out.println(row);
            }
            System.out.println();
        }

        public List<List<String>> solveNQueens(int n) {
            List<List<String>> result = new ArrayList<>();

            Chessboard chessboard = new Chessboard(n);
            int[] rows = new int[n];
            Arrays.fill(rows, -1);
            int col = 0;
            for (; ; ) {
                int row = rows[col] + 1;
                while (row < n && !chessboard.canSetQueen(row, col)) {
                    row++;
                }

                if (row >= n) {
                    rows[col] = -1;
                    col--;
                    if (col < 0) {
                        break;
                    }
                    chessboard.removeQueen(rows[col], col);
                } else {
                    rows[col] = row;
                    chessboard.setQueen(row, col);
                    if (col < n - 1) {
                        col++;
                    } else {
                        result.add(chessboard.toSolution());
                        chessboard.removeQueen(row, col);
                    }
                }
            }

            return result;
        }

        private static final class Chessboard {
            private final int size;
            private final int[][] board;

            Chessboard(int n) {
                size = n;
                board = new int[n][n];
            }

            private List<String> toSolution() {
                List<String> result = new ArrayList<>(size);

                StringBuilder builder = new StringBuilder(size);
                for (int[] row : board) {
                    for (int cell : row) {
                        if (cell < 0) {
                            builder.append('Q');
                        } else {
                            builder.append('.');
                        }
                    }
                    result.add(builder.toString());

                    builder.setLength(0);
                }
                return result;
            }

            boolean canSetQueen(int row, int col) {
                return board[row][col] == 0;
            }

            private void incPrimaryDiag(int row, int col) {
                int d = col - row;
                int col0 = Math.max(0, d);
                int row0 = col0 - d;
                for (int i = 0; col0 + i < size && row0 + i < size; i++) {
                    board[row0 + i][col0 + i]++;
                }
            }

            private void decPrimaryDiag(int row, int col) {
                int d = col - row;
                int col0 = Math.max(0, d);
                int row0 = col0 - d;
                for (int i = 0; col0 + i < size && row0 + i < size; i++) {
                    board[row0 + i][col0 + i]--;
                }
            }

            private void incSecondaryDiag(int row, int col) {
                int d = row + col;
                int col0 = Math.min(size - 1, d);
                int row0 = d - col0;
                for (int i = 0; row0 + i < size && col0 - i >= 0; i++) {
                    board[row0 + i][col0 - i]++;
                }
            }

            private void decSecondaryDiag(int row, int col) {
                int d = row + col;
                int col0 = Math.min(size - 1, d);
                int row0 = d - col0;
                for (int i = 0; row0 + i < size && col0 - i >= 0; i++) {
                    board[row0 + i][col0 - i]--;
                }
            }

            void setQueen(int row, int col) {
                board[row][col] = -Integer.MAX_VALUE;
                for (int i = 0; i < size; i++) {
                    board[row][i]++;
                    board[i][col]++;
                }

                incPrimaryDiag(row, col);
                incSecondaryDiag(row, col);
            }

            void removeQueen(int row, int col) {
                if (row < 0) {
                    return;
                }

                decSecondaryDiag(row, col);
                decPrimaryDiag(row, col);

                for (int i = 0; i < size; i++) {
                    board[i][col]--;
                    board[row][i]--;
                }

                board[row][col] = 0;
            }
        }
    }
}
