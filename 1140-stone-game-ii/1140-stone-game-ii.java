class Solution {
    int[][] dp;
    int[] suffix;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        suffix = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = suffix[i + 1] + piles[i];
        }
        dp = new int[n][n + 1];
        return dfs(piles, 0, 1);        
    }
    int dfs(int[] piles, int i, int m) {
        if (i >= piles.length) return 0;
        if (suffix[i] <= 0) return 0;
        if (dp[i][m] != 0) return dp[i][m];
        int best = 0;
        for (int x = 1; x <= 2 * m; x++) {
            int take = suffix[i] - suffix[Math.min(i + x, piles.length)];
            best = Math.max(best, take + suffix[i + Math.min(x, piles.length - i)] - dfs(piles, i + x, Math.max(m, x)));
        }
        dp[i][m] = suffix[i] - (suffix[i] - best);
        
        int res = 0;
        for (int x = 1; x <= 2 * m && i + x <= piles.length; x++) {
            res = Math.max(res, suffix[i] - dfs(piles, i + x, Math.max(m, x)));
        }
        return dp[i][m] = res;
    }
}