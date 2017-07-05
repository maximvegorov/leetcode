package leetcode;

public final class MaximalSquare {
    public static void main(String[] args) {
        System.out.println(
                new Solution()
                        .maximalSquare(
                                new char[][]{
                                        "11".toCharArray(),
                                        "11".toCharArray(),
                                        "01".toCharArray()}));
    }

    public static class Solution {
        private static ToIntMatrixResult toIntMatrix(char[][] source, int w, int h) {
            int[][] matrix = new int[h + 1][];
            int nonZeroCount = 0;
            for (int i = 0; i < h; i++) {
                matrix[i] = new int[w + 1];
                for (int j = 0; j < w; j++) {
                    matrix[i][j] = source[i][j] - '0';
                    if (matrix[i][j] > 0) {
                        nonZeroCount++;
                    }
                }
            }
            matrix[h] = new int[w + 1];
            return new ToIntMatrixResult(matrix, nonZeroCount);
        }

        public int maximalSquare(char[][] matrix) {
            int h = matrix.length;
            if (h == 0) {
                return 0;
            }
            int w = matrix[0].length;
            if (w == 0) {
                return 0;
            }

            int size = Math.min(w, h);

            ToIntMatrixResult result = toIntMatrix(matrix, w, h);
            if (result.getNonZeroCount() == 0) {
                return 0;
            } else if (result.getNonZeroCount() == w * h) {
                return size * size;
            }

            int[][] maxSizes = result.getMatrix();

            int totalMaxSize = 1;
            for (int l = 2; l <= size; l++) {
                for (int i = 0; i < h; i++) {
                    for (int j = 0; j < w; j++) {
                        int maxSize = maxSizes[i][j];
                        if (maxSize == 0) {
                            continue;
                        }

                        int r = j + maxSize;
                        int b = i + maxSize;

                        int maxRightDelta = Integer.MAX_VALUE;
                        for (int k = i; k < b; k += maxSizes[k][r]) {
                            maxRightDelta = Math.min(maxRightDelta, maxSizes[k][r]);
                            if (maxRightDelta == 0) {
                                break;
                            }
                        }

                        int maxBottomDelta = Integer.MAX_VALUE;
                        if (maxRightDelta > 0) {
                            for (int k = j; k < r; k += maxSizes[b][k]) {
                                maxBottomDelta = Math.min(maxBottomDelta, maxSizes[b][k]);
                                if (maxBottomDelta == 0) {
                                    break;
                                }
                            }
                        }

                        int delta = Math.min(Math.min(maxRightDelta, maxBottomDelta), maxSizes[b][r]);
                        maxSizes[i][j] += delta;
                        if (maxSizes[i][j] > totalMaxSize) {
                            totalMaxSize = maxSizes[i][j];
                        }
                    }
                }
                if (totalMaxSize < l) {
                    break;
                }
            }
            return totalMaxSize * totalMaxSize;
        }

        private static final class ToIntMatrixResult {
            private final int[][] matrix;
            private final int nonZeroCount;

            public ToIntMatrixResult(int[][] matrix, int nonZeroCount) {
                this.matrix = matrix;
                this.nonZeroCount = nonZeroCount;
            }

            public int[][] getMatrix() {
                return matrix;
            }

            public int getNonZeroCount() {
                return nonZeroCount;
            }
        }
    }
}
