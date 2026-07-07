class Solution {
    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        StringBuilder sb = new StringBuilder();
        int digitSum = 0;
        for (char c : s.toCharArray()) {
            int d = c - '0';
            digitSum += d;
            if (d != 0) {
                sb.append(c);
            }
        }
        long x = sb.length() == 0 ? 0 : Long.parseLong(sb.toString());
        return x * digitSum;
    }
}