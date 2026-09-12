class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] ivs = new int[n][3];
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            ivs[i][0] = intervals.get(i).get(0);
            ivs[i][1] = intervals.get(i).get(1);
            ivs[i][2] = intervals.get(i).get(2);
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> ivs[a][1] != ivs[b][1] ? ivs[a][1] - ivs[b][1] : ivs[a][0] - ivs[b][0]);

        int[][] sorted = new int[n][3];
        int[] origIdx = new int[n];
        for (int i = 0; i < n; i++) {
            sorted[i] = ivs[order[i]];
            origIdx[i] = order[i];
        }

        
        int K = 4;
        long[][] dp = new long[K + 1][n + 1];
        int[][][] chosen = new int[K + 1][n + 1][K];
        for (long[] row : dp) Arrays.fill(row, Long.MIN_VALUE / 2);
        for (int i = 0; i <= n; i++) dp[0][i] = 0;

        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < n; i++) {
                
                dp[k][i + 1] = dp[k][i];
                chosen[k][i + 1] = chosen[k][i];
                
                int lo = 0, hi = i - 1, j = -1;
                while (lo <= hi) {
                    int mid = (lo + hi) / 2;
                    if (sorted[mid][1] < sorted[i][0]) { j = mid; lo = mid + 1; }
                    else hi = mid - 1;
                }
                long prev = (j == -1) ? dp[k-1][0] : dp[k-1][j + 1];
                int[] prevChosen = (j == -1) ? chosen[k-1][0] : chosen[k-1][j + 1];
                long newScore = prev + sorted[i][2];
                if (newScore > dp[k][i + 1] ||
                    (newScore == dp[k][i + 1] && lexSmaller(origIdx[i], prevChosen, chosen[k][i + 1], k))) {
                    dp[k][i + 1] = newScore;
                    int[] nc = prevChosen.clone();
                    nc[k - 1] = origIdx[i];
                    Arrays.sort(nc, 0, k);
                    chosen[k][i + 1] = nc;
                }
            }
        }

        long best = Long.MIN_VALUE;
        int[] bestChosen = new int[0];
        for (int k = 1; k <= K; k++) {
            if (dp[k][n] > best || (dp[k][n] == best && lexSmallerArr(chosen[k][n], k, bestChosen, bestChosen.length))) {
                best = dp[k][n];
                bestChosen = Arrays.copyOf(chosen[k][n], k);
            }
        }
        return bestChosen;
    }

    boolean lexSmaller(int newIdx, int[] prev, int[] cur, int k) {
        int[] nc = prev.clone();
        nc[k - 1] = newIdx;
        Arrays.sort(nc, 0, k);
        for (int i = 0; i < k; i++) {
            if (nc[i] < cur[i]) return true;
            if (nc[i] > cur[i]) return false;
        }
        return false;
    }

    boolean lexSmallerArr(int[] a, int ka, int[] b, int kb) {
        int len = Math.min(ka, kb);
        for (int i = 0; i < len; i++) {
            if (a[i] < b[i]) return true;
            if (a[i] > b[i]) return false;
        }
        return ka < kb;
    }
}