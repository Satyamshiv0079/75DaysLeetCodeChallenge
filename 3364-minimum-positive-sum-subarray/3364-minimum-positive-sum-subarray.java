class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size(), best = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n && j - i + 1 <= r; j++) {
                sum += nums.get(j);
                if (j - i + 1 >= l && sum > 0) best = Math.min(best, sum);
            }
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}