class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] ndp = new long[k];
            int mod = num % k;
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    ndp[(int) ((long) r * mod % k)] += dp[r];
                }
            }
            ndp[mod] += 1;

            for (int r = 0; r < k; r++) {
                result[r] += ndp[r];
            }
            dp = ndp;
        }
        return result;
    }
}