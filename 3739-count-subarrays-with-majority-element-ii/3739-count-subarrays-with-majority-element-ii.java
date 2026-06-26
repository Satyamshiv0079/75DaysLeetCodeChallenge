class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++)
            pre[i + 1] = pre[i] + (nums[i] == target ? 1 : -1);

        int offset = n;
        int size = 2 * n + 2;
        int[] bit = new int[size + 1];
        long count = 0;

        for (int i = 0; i <= n; i++) {
            int val = pre[i] + offset;
            count += query(bit, val - 1);
            update(bit, val, size);
        }

        return count;
    }

    void update(int[] bit, int i, int size) {
        for (i++; i <= size; i += i & -i) bit[i]++;
    }

    int query(int[] bit, int i) {
        int sum = 0;
        for (i++; i > 0; i -= i & -i) sum += bit[i];
        return sum;
    }
}