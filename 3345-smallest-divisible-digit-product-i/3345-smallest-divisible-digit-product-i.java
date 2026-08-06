class Solution {
    public int smallestNumber(int n, int t) {
        while (true) {
            int prod = 1;
            for (char c : String.valueOf(n).toCharArray()) {
                prod *= c - '0';
            }
            if (prod % t == 0) return n;
            n++;
        }
    }
}