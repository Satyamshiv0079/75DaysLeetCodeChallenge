class Solution {
    static final int MOD = 1_000_000_007;
    long[] sumD, sumD2, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sumD = new long[4 * n];
        sumD2 = new long[4 * n];
        lazy = new long[4 * n];

        long ans = 0;
        int[] last = new int[100001];
        for (int i = 0; i < n; i++) {
            int prev = last[nums[i]];
            rangeUpdate(1, 0, n - 1, prev, i);
            ans = (ans + sumD2[1]) % MOD;
            last[nums[i]] = i + 1;
        }
        return (int) ans;
    }

    void rangeUpdate(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return;
        if (ql <= l && r <= qr) {
            long cnt = r - l + 1;
            sumD2[node] = (sumD2[node] + 2 * sumD[node] % MOD + cnt) % MOD;
            sumD[node] = (sumD[node] + cnt) % MOD;
            lazy[node]++;
            return;
        }
        pushDown(node, l, r);
        int mid = (l + r) / 2;
        rangeUpdate(2*node, l, mid, ql, qr);
        rangeUpdate(2*node+1, mid+1, r, ql, qr);
        sumD[node] = (sumD[2*node] + sumD[2*node+1]) % MOD;
        sumD2[node] = (sumD2[2*node] + sumD2[2*node+1]) % MOD;
    }

    void pushDown(int node, int l, int r) {
        if (lazy[node] == 0) return;
        int mid = (l + r) / 2;
        applyLazy(2*node, l, mid, lazy[node]);
        applyLazy(2*node+1, mid+1, r, lazy[node]);
        lazy[node] = 0;
    }

    void applyLazy(int node, int l, int r, long add) {
        long cnt = r - l + 1;
        sumD2[node] = (sumD2[node] + 2 * add % MOD * sumD[node] % MOD + add % MOD * add % MOD * cnt) % MOD;
        sumD[node] = (sumD[node] + add * cnt) % MOD;
        lazy[node] += add;
    }
}