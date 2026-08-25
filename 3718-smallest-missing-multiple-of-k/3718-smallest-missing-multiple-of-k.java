class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);
        int mul = k;
        while (set.contains(mul)) mul += k;
        return mul;
    }
}