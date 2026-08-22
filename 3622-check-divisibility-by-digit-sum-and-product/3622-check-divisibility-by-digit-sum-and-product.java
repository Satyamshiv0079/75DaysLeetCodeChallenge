class Solution {
    public boolean checkDivisibility(int n) {
        int sum = 0, product = 1, x = n;
        while (x > 0) {
            int d = x % 10;
            sum += d;
            product *= d;
            x /= 10;
        }
        return n % (sum + product) == 0;
    }
}