class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length, max = 0;
        for (int dy = -(n - 1); dy <= n - 1; dy++) {
            for (int dx = -(n - 1); dx <= n - 1; dx++) {
                int count = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int ni = i + dy, nj = j + dx;
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                            if (img1[i][j] == 1 && img2[ni][nj] == 1) {
                                count++;
                            }
                        }
                    }
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}