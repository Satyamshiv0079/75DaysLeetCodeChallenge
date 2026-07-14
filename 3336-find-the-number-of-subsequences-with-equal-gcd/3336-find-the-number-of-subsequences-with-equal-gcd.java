class Solution {
    static final int MOD = 1_000_000_007;
    public int subsequencePairCount(int[] nums) {
        int max = 200;
        long[][] dp = new long[max + 1][max + 1];
        dp[0][0] = 1;

         for (int num : nums) {
            long[][] ndp = new long[max + 1][max + 1];
            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    long val = dp[g1][g2];
                    ndp[g1][g2] = (ndp[g1][g2] + val) % MOD;
                    int ng1 = g1 == 0 ? num : gcd(g1, num);
                    ndp[ng1][g2] = (ndp[ng1][g2] + val) % MOD;
                    int ng2 = g2 == 0 ? num : gcd(g2, num);
                    ndp[g1][ng2] = (ndp[g1][ng2] + val) % MOD;
                }
            }
            dp = ndp;
        }
        long ans = 0;
        for (int g = 1; g <= max; g++)
            ans = (ans + dp[g][g]) % MOD;
        return (int) ans;
    }
     int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}