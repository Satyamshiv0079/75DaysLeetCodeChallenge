class Solution {
    static final int MOD = 1_000_000_007;

    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long total = 0;
        for (char c : s.toCharArray()) {
            int i = c - 'a';
            long add = (total - last[i] + 1 + MOD) % MOD;
            total = (total + add) % MOD;
            last[i] = (last[i] + add) % MOD;
        }
        return (int) total;
    }
}