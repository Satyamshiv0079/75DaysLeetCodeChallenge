class Solution {
    int k;
    long[][] tree;
    long[] prod;
    int n;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        
        this.k = k;
        this.n = nums.length;
        tree = new long[4 * n][k];
        prod = new long[4 * n];
        build(nums, 1, 0, n - 1);

        int[] result = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0], val = queries[q][1], start = queries[q][2], x = queries[q][3];
            nums[idx] = val;
            update(1, 0, n - 1, idx, val);
            long[] merged = query(1, 0, n - 1, start, n - 1);
            result[q] = (int) merged[x];
        }
        return result;
    }

    void build(int[] nums, int node, int l, int r) {
        if (l == r) {
            prod[node] = nums[l] % k;
            tree[node][(int)(prod[node])] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(nums, 2*node, l, mid);
        build(nums, 2*node+1, mid+1, r);
        merge(node);
    }

    void merge(int node) {
        long lProd = prod[2*node], rProd = prod[2*node+1];
        prod[node] = lProd * rProd % k;
        for (int r = 0; r < k; r++) tree[node][r] = 0;
        // left segment prefixes stay as is
        for (int r = 0; r < k; r++) tree[node][r] += tree[2*node][r];
        // right segment prefixes: multiply by lProd
        for (int r = 0; r < k; r++) {
            int newR = (int)(r * lProd % k);
            tree[node][newR] += tree[2*node+1][r];
        }
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            prod[node] = val % k;
            for (int i = 0; i < k; i++) tree[node][i] = 0;
            tree[node][(int)(prod[node])] = 1;
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(2*node, l, mid, idx, val);
        else update(2*node+1, mid+1, r, idx, val);
        merge(node);
    }

    long[] query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return tree[node].clone();
        }
        int mid = (l + r) / 2;
        if (qr <= mid) return query(2*node, l, mid, ql, qr);
        if (ql > mid) return query(2*node+1, mid+1, r, ql, qr);
        long[] left = query(2*node, l, mid, ql, qr);
        long[] right = query(2*node+1, mid+1, r, ql, qr);
        // merge left and right
        long lProd = queryProd(2*node, l, mid, ql, mid);
        long[] merged = new long[k];
        for (int i = 0; i < k; i++) merged[i] += left[i];
        for (int r2 = 0; r2 < k; r2++) {
            int newR = (int)(r2 * lProd % k);
            merged[newR] += right[r2];
        }
        return merged;
    }

    long queryProd(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) return prod[node];
        int mid = (l + r) / 2;
        if (qr <= mid) return queryProd(2*node, l, mid, ql, qr);
        if (ql > mid) return queryProd(2*node+1, mid+1, r, ql, qr);
        return queryProd(2*node, l, mid, ql, mid) * queryProd(2*node+1, mid+1, r, mid+1, qr) % k;
    }
}