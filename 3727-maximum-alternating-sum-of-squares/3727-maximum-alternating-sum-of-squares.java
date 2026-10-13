class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(nums[i]);
        }
        Arrays.sort(nums);

        int negCount = n / 2;
        long ans = 0;

        for (int i = 0; i < negCount; i++) {
            ans -= (long) nums[i] * nums[i];
        }

        for (int i = negCount; i < n; i++) {
            ans += (long) nums[i] * nums[i];
        }

        return ans;
    }
}