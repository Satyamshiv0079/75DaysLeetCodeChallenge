class Solution {
    String[] map = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    void backtrack(String digits, int i, StringBuilder sb, List<String> res) {
        if (i == digits.length()) { res.add(sb.toString()); return; }
        for (char c : map[digits.charAt(i) - '2'].toCharArray()) {
            sb.append(c);
            backtrack(digits, i + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}