class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String res = "";
        for (int i = 0; i < n; i++) {
            int ones = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '1') ones++;
                if (ones == k) {
                    String sub = s.substring(i, j + 1);
                    if (res.isEmpty() || sub.length() < res.length() || (sub.length() == res.length() && sub.compareTo(res) < 0)) {
                        res = sub;
                    }
                    break;
                }
            }
        }
        return res;
    }
}