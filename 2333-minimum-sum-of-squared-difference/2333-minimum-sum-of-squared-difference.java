class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = Math.abs(nums1[i] - nums2[i]);

        long k = (long) k1 + k2;
        int lo = 0, hi = 100000;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            long need = 0;
            for (int d : diff) need += Math.max(0, d - mid);
            if (need <= k) hi = mid;
            else lo = mid + 1;
        }
        int maxD = lo;

        long need = 0;
        for (int d : diff) need += Math.max(0, d - maxD);
        long leftover = k - need;

        long ans = 0;
        for (int d : diff) {
            long cur = Math.min(d, maxD);
            if (cur == maxD && leftover > 0) {
                cur = Math.max(0, cur - 1);
                leftover--;
            }
            ans += cur * cur;
        }
        return ans;
    }
}