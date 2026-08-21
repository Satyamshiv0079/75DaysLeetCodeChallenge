class Solution {
    public long findKthSmallest(int[] coins, int k) {
        List<Long> valid = new ArrayList<>();
        int n = coins.length;
        boolean[] skip = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && !skip[i] && coins[i] % coins[j] == 0) skip[i] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (!skip[i]) valid.add((long) coins[i]);
        }
        long lo = 1, hi = (long) coins[0] * k;
        for (long c : valid) hi = Math.min(hi, c * k);

        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (count(valid, mid) >= k) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
    long count(List<Long> coins, long x) {
        int n = coins.size();
        long total = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = Integer.bitCount(mask);
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcm = lcm(lcm, coins.get(i));
                    if (lcm > x) break;
                }
            }
            if (bits % 2 == 1) total += x / lcm;
            else total -= x / lcm;
        }
        return total;
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}