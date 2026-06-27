class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge((long) n, 1, Integer::sum);

        int ans = 1;
        if (freq.getOrDefault(1L, 0) > 0)
            ans = freq.get(1L) % 2 == 0 ? freq.get(1L) - 1 : freq.get(1L);

        for (long x : freq.keySet()) {
            if (x == 1) continue;
            int len = 0;
            long cur = x;
            while (freq.getOrDefault(cur, 0) >= 2) {
                len += 2;
                cur = cur * cur;
            }
            if (freq.getOrDefault(cur, 0) >= 1) len++;
            else if (len > 0) len--;
            ans = Math.max(ans, len);
        }

        return ans;
    }
}