class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 50000;
        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        
        long[] cntDiv = new long[max + 1];
        for (int g = 1; g <= max; g++)
            for (int mul = g; mul <= max; mul += g)
                cntDiv[g] += freq[mul];

        
        long[] pairCnt = new long[max + 1];
        for (int g = max; g >= 1; g--) {
            long c = cntDiv[g];
            pairCnt[g] = c * (c - 1) / 2;
            for (int mul = 2 * g; mul <= max; mul += g)
                pairCnt[g] -= pairCnt[mul];
        }

        
        long[] prefix = new long[max + 2];
        for (int g = 1; g <= max; g++)
            prefix[g] = prefix[g - 1] + pairCnt[g];

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];
            int lo = 1, hi = max;
            while (lo < hi) {
                int mid = (lo + hi) / 2;
                if (prefix[mid] > q) hi = mid;
                else lo = mid + 1;
            }
            ans[i] = lo;
        }
        return ans;
    }
}