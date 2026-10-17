class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) sorted[i] = new int[]{nums[i], i};
        Arrays.sort(sorted, (a, b) -> {
            int da = digitSum(a[0]), db = digitSum(b[0]);
            return da != db ? da - db : a[0] - b[0];
        });

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) pos[sorted[i][1]] = i;

        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i] || pos[i] == i) { visited[i] = true; continue; }
            int cycleLen = 0, j = i;
            while (!visited[j]) { visited[j] = true; j = pos[j]; cycleLen++; }
            swaps += cycleLen - 1;
        }
        return swaps;
    }

    int digitSum(int n) {
        int s = 0;
        while (n > 0) { s += n % 10; n /= 10; }
        return s;
    }
}