class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] inv : invocations) adj.get(inv[0]).add(inv[1]);

        boolean[] suspicious = new boolean[n];
        dfs(k, adj, suspicious);

        for (int[] inv : invocations) {
            if (!suspicious[inv[0]] && suspicious[inv[1]]) {
                return getAllMethods(n);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) res.add(i);
        }
        return res;
    }

    void dfs(int node, List<List<Integer>> adj, boolean[] suspicious) {
        suspicious[node] = true;
        for (int nb : adj.get(node)) {
            if (!suspicious[nb]) dfs(nb, adj, suspicious);
        }
    }

    List<Integer> getAllMethods(int n) {
        List<Integer> all = new ArrayList<>();
        for (int i = 0; i < n; i++) all.add(i);
        return all;
    }
}