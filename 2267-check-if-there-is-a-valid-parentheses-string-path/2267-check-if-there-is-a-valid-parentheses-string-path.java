class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n) % 2 == 0) return false;
        boolean[][][] dp = new boolean [m][n][(m + n) / 2 + 1];
        dp[0][0][grid[0][0] == '(' ? 1 : 0] = grid[0][0] == '(';
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                int delta = grid[i][j] == '(' ? 1 : -1;
                for (int k = 0; k <= (m + n) / 2; k++) {
                    int pk = k - delta;
                    if (pk < 0 || pk > (m + n) / 2) continue;
                    if (i > 0 && dp[i-1][j][pk]) dp[i][j][k] = true;
                    if (j > 0 && dp[i][j-1][pk]) dp[i][j][k] = true;
                }
            }
        }
        return dp[m-1][n-1][0];
    }
}