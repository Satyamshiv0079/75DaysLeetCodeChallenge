class Solution {
    static final long MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        return (int) comb(n + k - 1, 2 * k);
    }

    long comb(int n, int r) {
        if (r > n) return 0;
        long[] dp = new long[r + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = Math.min(i, r); j >= 1; j--)
                dp[j] = (dp[j] + dp[j - 1]) % MOD;
        return dp[r];
    }
}