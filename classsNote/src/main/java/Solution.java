public class Solution {
    private int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] an(int[][] bubbles) {
        int rows = bubbles.length;
        int cols = bubbles[0].length;
        boolean[][] toExplode = new boolean[rows][cols];

        // Step 1: Find bubbles to explode
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (bubbles[i][j] == 0) continue;
                int count = 0;
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && bubbles[ni][nj] == bubbles[i][j]) {
                        count++;
                    }
                }
                if (count >= 2) {
                    toExplode[i][j] = true;
                    for (int[] dir : dirs) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (ni >= 0 && ni < rows && nj >= 0 && nj < cols && bubbles[ni][nj] == bubbles[i][j]) {
                            toExplode[ni][nj] = true;
                        }
                    }
                }
            }
        }

        // Step 2: Explode bubbles
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (toExplode[i][j]) {
                    bubbles[i][j] = 0;
                }
            }
        }

        // Step 3: Gravity effect
        for (int j = 0; j < cols; j++) {
            int writeIndex = rows - 1;
            for (int i = rows - 1; i >= 0; i--) {
                if (bubbles[i][j] != 0) {
                    bubbles[writeIndex][j] = bubbles[i][j];
                    if (writeIndex != i) {
                        bubbles[i][j] = 0;
                    }
                    writeIndex--;
                }
            }
        }

        return bubbles;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] bubbles = {
                {3, 1, 2, 1},
                {1, 1, 1, 4},
                {3, 1, 2, 2},
                {3, 3, 3, 4}
        };
        int[][] result = sol.an(bubbles);
        for (int i = 0; i < result.length; ++i) {
            for (int j = 0; j < result[i].length; ++j) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}