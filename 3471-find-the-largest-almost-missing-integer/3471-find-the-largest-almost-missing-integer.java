class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int ans = -1;
        for (int x = 50; x >= 0; x--) {
            int count = 0;
            for (int i = 0; i + k <= n; i++) {
                boolean found = false;
                for (int j = i; j < i + k; j++) {
                    if (nums[j] == x) {
                        found = true;
                        break;
                    }
                }
                if (found) count++;
                if (count > 1) break;
            }
            if (count == 1) {
                ans = x;
                break;
            }
        }
        return ans;
    }
}