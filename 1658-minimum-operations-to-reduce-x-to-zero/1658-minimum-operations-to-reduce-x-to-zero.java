class Solution {
    public int minOperations(int[] nums, int x) {
        int total = 0;
        for (int n : nums) total += n;
        int target = total - x;
        if (target < 0) return -1;
        if (target == 0) return nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, maxLen = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target)) {
                maxLen = Math.max(maxLen, i - map.get(sum - target));
            }
            map.putIfAbsent(sum, i);
        }
        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}