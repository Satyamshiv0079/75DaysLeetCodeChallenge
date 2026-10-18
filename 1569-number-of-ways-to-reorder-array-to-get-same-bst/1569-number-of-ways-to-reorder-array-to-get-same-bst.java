class Solution {
    static final long MOD = 1_000_000_007;
    long[][] C;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        C = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++)
                C[i][j] = (C[i-1][j-1] + C[i-1][j]) % MOD;
        }
        List<Integer> list = new ArrayList<>();
        for (int x : nums) list.add(x);
        return (int)((dfs(list) - 1 + MOD) % MOD);
    }

    long dfs(List<Integer> nums) {
        if (nums.size() <= 2) return 1;
        int root = nums.get(0);
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < root) left.add(nums.get(i));
            else right.add(nums.get(i));
        }
        long l = dfs(left), r = dfs(right);
        int ls = left.size(), rs = right.size();
        return C[ls + rs][ls] % MOD * l % MOD * r % MOD;
    }
}