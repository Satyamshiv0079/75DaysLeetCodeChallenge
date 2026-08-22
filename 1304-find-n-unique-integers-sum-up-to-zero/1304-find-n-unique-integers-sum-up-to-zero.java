class Solution {
    public int[] sumZero(int n) {
        int[] res = new int[n];
        for (int i = 0; i < n /2; i++) {
            res[i] = i + 1;
            res[n - 1 - i] = -(i + 1);
        }
        return res;   
    }
}