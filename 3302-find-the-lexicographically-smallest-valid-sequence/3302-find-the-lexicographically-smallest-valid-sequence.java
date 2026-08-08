class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        int[] suf = new int[n + 1];
        int p = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];
            if (p >= 0 && word1.charAt(i) == word2.charAt(p)) {
                suf[i]++;
                p--;
            }
        }

        int[] res = new int[m];
        int matched = 0;
        boolean used = false;

        for (int i = 0; i < n && matched < m; i++) {
            if (word1.charAt(i) == word2.charAt(matched)) {
                res[matched++] = i;
            } else if (!used && suf[i + 1] >= m - matched - 1) {
                res[matched++] = i;
                used = true;
            }
        }

        return matched == m ? res : new int[0];
    }
}