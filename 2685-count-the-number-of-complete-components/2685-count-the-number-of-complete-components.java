class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            List<Integer> comp = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int node = queue.poll();
                comp.add(node);
                for (int nb : adj.get(node))
                    if (!visited[nb]) {
                        visited[nb] = true;
                        queue.offer(nb);
                    }
            }
            int m = comp.size();
            int edgeCount = comp.stream().mapToInt(v -> adj.get(v).size()).sum() / 2;
            if (edgeCount == m * (m - 1) / 2) count++;
        }
        return count;
    }
}