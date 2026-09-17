class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] prefix = new int[n];
        Arrays.fill(prefix, Integer.MAX_VALUE);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0, best = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += arr[i];
            if (map.containsKey(sum - target)) {
                int j = map.get(sum - target);
                int len = i - j;
                if (j >= 0 && prefix[j] != Integer.MAX_VALUE)
                    best = Math.min(best, prefix[j] + len);
                prefix[i] = Math.min(i > 0 ? prefix[i-1] : Integer.MAX_VALUE, len);
            } else {
                prefix[i] = i > 0 ? prefix[i-1] : Integer.MAX_VALUE;
            }
            map.put(sum, i);
        }
        return best == Integer.MAX_VALUE ? -1 : best;
    }
}