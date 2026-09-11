class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> seen = new HashSet<>();
        int n = digits.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || j == k || i == k) continue;
                    if (digits[i] == 0) continue;
                    if (digits[k] % 2 != 0) continue;
                    seen.add(digits[i] * 100 + digits[j] * 10 + digits[k]);
                }
            }
        }
        return seen.size();
    }
}