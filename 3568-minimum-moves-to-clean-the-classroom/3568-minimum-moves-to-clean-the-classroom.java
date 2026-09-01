class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        int sx = 0, sy = 0, lCount = 0;
        int[][] lIdx = new int[m][n];
        for (int[] row : lIdx) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') { sx = i; sy = j; }
                else if (c == 'L') { lIdx[i][j] = lCount++; }
            }

        int full = (1 << lCount) - 1;
        int[][][] best = new int[m][n][1 << lCount];
        for (int[][] a : best) for (int[] b : a) Arrays.fill(b, -1);

        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sx, sy, 0, energy, 0});
        best[sx][sy][0] = energy;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], mask = cur[2], e = cur[3], steps = cur[4];

            if (mask == full) return steps;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (classroom[nx].charAt(ny) == 'X') continue;
                if (e == 0) continue;

                int ne = e - 1;
                int nm = mask;
                char nc = classroom[nx].charAt(ny);
                if (nc == 'R') ne = energy;
                if (nc == 'L' && lIdx[nx][ny] >= 0) nm |= (1 << lIdx[nx][ny]);

                if (ne > best[nx][ny][nm]) {
                    best[nx][ny][nm] = ne;
                    queue.offer(new int[]{nx, ny, nm, ne, steps + 1});
                }
            }
        }
        return -1;
    }
}