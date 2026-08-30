class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }
        int lo = Math.min(minIdx, maxIdx);
        int hi = Math.max(minIdx, maxIdx);
        int fromFront = hi + 1;
        int fromBack = n - lo;
        int both = Math.min(lo + 1 + n - hi, hi + 1 + n - 1 - lo);
        return Math.min(fromFront, Math.min(fromBack, both));
    }
}