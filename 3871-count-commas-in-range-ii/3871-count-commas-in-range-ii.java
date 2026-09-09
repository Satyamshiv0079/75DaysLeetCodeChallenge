class Solution {
    public long countCommas(long n) {
        long total = 0;
        long lower = 1000;
        int commas = 1;

        while (lower <= n) {
            long upper = lower * 1000 - 1;
            long hi = Math.min(n, upper);

            total += (hi - lower + 1) * commas;

            lower *= 1000;
            commas++;
        }

        return total;
    }
}