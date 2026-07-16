class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixGcd = new long[n];
        int mx = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            prefixGcd[i] = gcd(nums[i], mx);
        }
        Arrays.sort(prefixGcd);
        long ans = 0;
        int lo = 0, hi = n - 1;
        while (lo < hi) {
            ans += gcd(prefixGcd[lo], prefixGcd[hi]);
            lo++; hi--;
        }
        return ans;
    }
    long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }
}