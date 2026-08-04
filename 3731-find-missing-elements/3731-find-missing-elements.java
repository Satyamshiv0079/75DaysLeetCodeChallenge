class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n : nums) {
            set.add(n);
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }
        return res;
    }
}