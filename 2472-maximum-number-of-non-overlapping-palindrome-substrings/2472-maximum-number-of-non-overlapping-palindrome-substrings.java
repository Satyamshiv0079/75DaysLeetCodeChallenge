class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        int[] dp = new int[n + 1];

        for (int i = k; i <= n; i++) {
            dp[i] = dp[i - 1];
            
            for (int c = i - 1; c >= 0; c--) {
                int start = i - k;
                
                if (isPalin(s, start, i - 1)) {
                    dp[i] = Math.max(dp[i], dp[i - k] + 1);
                }
                
                if (k % 2 == 0 && start >= 0 && isPalin(s, start, i - 1)) {
                    dp[i] = Math.max(dp[i], dp[i - k] + 1);
                }
                break;
            }
            
            if (i >= k && isPalin(s, i - k, i - 1))
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            if (i >= k + 1 && isPalin(s, i - k - 1, i - 1))
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
        }
        return dp[n];
    }

    boolean isPalin(String s, int l, int r) {
        if (l < 0 || r >= s.length()) return false;
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }
}