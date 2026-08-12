class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0, max = 0;
        for (int right = 0; right < nums.length; right++) {
            freq.merge(nums[right], 1, Integer::sum);
            while (freq.get(nums[right]) > k) {
                freq.merge(nums[left], -1, Integer::sum);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}