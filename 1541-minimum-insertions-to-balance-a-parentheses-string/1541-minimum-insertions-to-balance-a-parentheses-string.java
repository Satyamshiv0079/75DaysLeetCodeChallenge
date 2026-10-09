class Solution {
    public int minInsertions(String s) {
        int open = 0, ans = 0, i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '(') { open++; i++; }
            else {
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') i += 2;
                else { ans++; i++; }
                if (open > 0) open--;
                else ans++;
            }
        }
        return ans + 2 * open;
    }
}