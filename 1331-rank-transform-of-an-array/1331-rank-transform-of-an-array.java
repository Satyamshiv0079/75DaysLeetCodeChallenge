class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> arr[a] - arr[b]);
        int[] ans = new int[n];
        int rank = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0 || arr[idx[i]] != arr[idx[i-1]]) rank++;
            ans[idx[i]] = rank;
        }
        return ans;
    }
}