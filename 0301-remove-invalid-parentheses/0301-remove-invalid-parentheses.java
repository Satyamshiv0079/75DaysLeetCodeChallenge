class Solution {
    Set<String> res = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int rmL = 0, rmR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') rmL++;
            else if (c == ')') {
                if (rmL > 0) rmL--;
                else rmR++;
            }
        }
        dfs(s, 0, rmL, rmR, 0, new StringBuilder());
        return new ArrayList<>(res);
    }

    void dfs(String s, int i, int rmL, int rmR, int open, StringBuilder sb) {
        if (i == s.length()) {
            if (rmL == 0 && rmR == 0 && open == 0) res.add(sb.toString());
            return;
        }
        char c = s.charAt(i);
        if (c == '(' && rmL > 0) dfs(s, i+1, rmL-1, rmR, open, sb);
        if (c == ')' && rmR > 0) dfs(s, i+1, rmL, rmR-1, open, sb);
        sb.append(c);
        if (c == '(') dfs(s, i+1, rmL, rmR, open+1, sb);
        else if (c == ')') { if (open > 0) dfs(s, i+1, rmL, rmR, open-1, sb); }
        else dfs(s, i+1, rmL, rmR, open, sb);
        sb.deleteCharAt(sb.length()-1);
    }
}