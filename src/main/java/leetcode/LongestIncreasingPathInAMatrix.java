package leetcode;

import java.util.*;

public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        System.out.println(
                new Solution().longestIncreasingPath(
                        new int[][]{
                                {3, 4, 5},
                                {3, 2, 6},
                                {2, 2, 1}}));
    }

    static class Solution {
        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null || matrix.length == 0)
                return 0;

            int colCount = matrix[0].length;

            boolean[][] visited = new boolean[matrix.length][colCount];

            Cell newLevelCell = new Cell(-1, -1);
            int maxLength = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < colCount; j++) {
                    if (visited[i][j])
                        continue;

                    Queue<Cell> queue = new ArrayDeque<>(Arrays.asList(new Cell(i, j), newLevelCell));
                    int length = 0;
                    while (!queue.isEmpty()) {
                        Cell cur = queue.remove();
                        if (cur == newLevelCell) {
                            length++;
                            if (!queue.isEmpty())
                                queue.add(newLevelCell);
                            continue;
                        }

                        int curValue = matrix[cur.getRow()][cur.getCol()];
                        visited[cur.getRow()][cur.getCol()] = true;

                        int top = cur.getRow() - 1;
                        int bottom = cur.getRow() + 1;
                        int left = cur.getCol() - 1;
                        int right = cur.getCol() + 1;

                        if (top >= 0 && matrix[top][cur.getCol()] > curValue) {
                            queue.add(new Cell(top, cur.getCol()));
                        }
                        if (right < colCount && matrix[cur.getRow()][right] > curValue) {
                            queue.add(new Cell(cur.getRow(), right));
                        }
                        if (bottom < matrix.length && matrix[bottom][cur.getCol()] > curValue) {
                            queue.add(new Cell(bottom, cur.getCol()));
                        }
                        if (left >= 0 && matrix[cur.getRow()][left] > curValue) {
                            queue.add(new Cell(cur.getRow(), left));
                        }
                    }

                    if (length > maxLength)
                        maxLength = length;
                }
            }
            return maxLength;
        }

        static final class Cell {
            private final int row;
            private final int col;

            Cell(int row, int col) {
                this.row = row;
                this.col = col;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Cell cell = (Cell) o;
                return row == cell.row &&
                        col == cell.col;
            }

            @Override
            public int hashCode() {
                return Objects.hash(row, col);
            }

            @Override
            public String toString() {
                return "Cell{" +
                        "row=" + row +
                        ", col=" + col +
                        '}';
            }

            public int getRow() {
                return row;
            }

            public int getCol() {
                return col;
            }
        }
    }
}
