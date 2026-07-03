class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(new int[]{e[1], e[2]});

        int[] sortedCosts = Arrays.stream(edges).mapToInt(e -> e[2]).distinct().sorted().toArray();
        int lo = 0, hi = sortedCosts.length - 1, ans = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int minEdge = sortedCosts[mid];
            if (check(n, adj, online, k, minEdge)) {
                ans = minEdge;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }

    boolean check(int n, List<int[]>[] adj, boolean[] online, long k, int minEdge) {
        int[] indegree = new int[n];
        for (int u = 0; u < n; u++)
            for (int[] e : adj[u])
                if (e[1] >= minEdge) indegree[e[0]]++;

        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 0) queue.offer(i);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            boolean canUse = (u == 0 || u == n - 1 || online[u]);
            for (int[] e : adj[u]) {
                int v = e[0], cost = e[1];
                if (cost < minEdge) continue;
                if (canUse && dp[u] != Long.MAX_VALUE) {
                    long newCost = dp[u] + cost;
                    if (newCost < dp[v]) dp[v] = newCost;
                }
                indegree[v]--;
                if (indegree[v] == 0) queue.offer(v);
            }
        }
        return dp[n - 1] != Long.MAX_VALUE && dp[n - 1] <= k;
    }
}