package leetcode;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

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

            PriorityQueue<Cell> unprocessed = new PriorityQueue<>(
                    Comparator.comparingInt(cell -> -matrix[cell.getRow()][cell.getCol()]));
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < colCount; j++) {
                    unprocessed.add(new Cell(i, j));
                }
            }

            int[][] maxLengths = new int[matrix.length][colCount];

            int totalMaxLength = 0;
            while (!unprocessed.isEmpty()) {
                Cell cur = unprocessed.remove();
                int curValue = matrix[cur.getRow()][cur.getCol()];

                int top = cur.getRow() - 1;
                int bottom = cur.getRow() + 1;
                int left = cur.getCol() - 1;
                int right = cur.getCol() + 1;

                int maxLength = 0;
                if (top >= 0 && matrix[top][cur.getCol()] > curValue) {
                    maxLength = Math.max(0, maxLengths[top][cur.getCol()]);
                }
                if (right < colCount && matrix[cur.getRow()][right] > curValue) {
                    maxLength = Math.max(maxLength, maxLengths[cur.getRow()][right]);
                }
                if (bottom < matrix.length && matrix[bottom][cur.getCol()] > curValue) {
                    maxLength = Math.max(maxLength, maxLengths[bottom][cur.getCol()]);
                }
                if (left >= 0 && matrix[cur.getRow()][left] > curValue) {
                    maxLength = Math.max(maxLength, maxLengths[cur.getRow()][left]);
                }
                maxLength++;

                maxLengths[cur.getRow()][cur.getCol()] = maxLength;

                totalMaxLength = Math.max(totalMaxLength, maxLength);
            }
            return totalMaxLength;
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
