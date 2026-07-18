class Solution {
    public int findGCD(int[] nums) {
        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();
        return gcd(min, max);
    }
    int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
}