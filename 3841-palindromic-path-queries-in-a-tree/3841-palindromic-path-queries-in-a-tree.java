class Solution {
    int[] parent, depth, heavy, head, pos, nodeAt;
    int[] seg;
    char[] chars;
    int n, timer;
    List<List<Integer>> adj;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        chars = s.toCharArray();
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) { adj.get(e[0]).add(e[1]); adj.get(e[1]).add(e[0]); }

        parent = new int[n]; depth = new int[n];
        heavy = new int[n]; int[] sz = new int[n];
        Arrays.fill(heavy, -1);
        dfsSize(0, -1, sz);

        head = new int[n]; pos = new int[n]; nodeAt = new int[n];
        timer = 0;
        decompose(0, -1, 0);

        seg = new int[4 * n];
        for (int i = 0; i < n; i++) update(1, 0, n-1, pos[i], 1 << (chars[i] - 'a'));

        List<Boolean> ans = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                chars[u] = parts[2].charAt(0);
                update(1, 0, n-1, pos[u], 1 << (chars[u] - 'a'));
            } else {
                int u = Integer.parseInt(parts[1]), v = Integer.parseInt(parts[2]);
                int xor = pathXor(u, v);
                ans.add(Integer.bitCount(xor) <= 1);
            }
        }
        return ans;
    }

    void dfsSize(int u, int p, int[] sz) {
        parent[u] = p; sz[u] = 1;
        int maxSz = 0;
        for (int v : adj.get(u)) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfsSize(v, u, sz);
            sz[u] += sz[v];
            if (sz[v] > maxSz) { maxSz = sz[v]; heavy[u] = v; }
        }
    }

    void decompose(int u, int p, int h) {
        head[u] = h; pos[u] = timer; nodeAt[timer++] = u;
        if (heavy[u] != -1) decompose(heavy[u], u, h);
        for (int v : adj.get(u)) if (v != p && v != heavy[u]) decompose(v, u, v);
    }

    void update(int node, int l, int r, int idx, int val) {
        if (l == r) { seg[node] = val; return; }
        int mid = (l+r)/2;
        if (idx <= mid) update(2*node, l, mid, idx, val);
        else update(2*node+1, mid+1, r, idx, val);
        seg[node] = seg[2*node] ^ seg[2*node+1];
    }

    int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return seg[node];
        int mid = (l+r)/2;
        return query(2*node, l, mid, ql, qr) ^ query(2*node+1, mid+1, r, ql, qr);
    }

    int pathXor(int u, int v) {
        int res = 0;
        while (head[u] != head[v]) {
            if (depth[head[u]] < depth[head[v]]) { int t=u; u=v; v=t; }
            res ^= query(1, 0, n-1, pos[head[u]], pos[u]);
            u = parent[head[u]];
        }
        if (depth[u] > depth[v]) { int t=u; u=v; v=t; }
        res ^= query(1, 0, n-1, pos[u], pos[v]);
        return res;
    }
}