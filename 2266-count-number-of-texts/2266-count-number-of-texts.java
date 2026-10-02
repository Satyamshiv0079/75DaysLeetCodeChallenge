class Solution {
    static final int MOD = 1_000_000_007;

    public int countTexts(String s) {
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int max = (c == '7' || c == '9') ? 4 : 3;
            dp[i] = 0;
            for (int j = 1; j <= max && i - j >= 0; j++) {
                if (s.charAt(i - j) == c) dp[i] = (dp[i] + dp[i - j]) % MOD;
                else break;
            }
        }
        return (int) dp[n];
    }
}