class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(new int[]{e[1], e[2]});

        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][1] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 1});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], u = cur[1], c = cur[2];
            if (d > dist[u][c]) continue;
            if (u == n - 1) return d;
            for (int[] e : adj[u]) {
                int v = e[0];
                int nc = labels.charAt(u) == labels.charAt(v) ? c + 1 : 1;
                if (nc > k) continue;
                int nd = d + e[1];
                if (nd < dist[v][nc]) {
                    dist[v][nc] = nd;
                    pq.offer(new int[]{nd, v, nc});
                }
            }
        }
        return -1;
    }
}