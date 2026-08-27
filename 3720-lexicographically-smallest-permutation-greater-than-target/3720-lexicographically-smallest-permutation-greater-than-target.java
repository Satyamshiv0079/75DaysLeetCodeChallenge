class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        char[] res = new char[n];
        int[][] savedFreqs = new int[n][26];
        int i = 0;

        while (i < n) {
            char t = target.charAt(i);
            if (freq[t - 'a'] > 0) {
                savedFreqs[i] = freq.clone();
                res[i] = t;
                freq[t - 'a']--;
                i++;
            } else {
                char bump = findBump(freq, t);
                if (bump != 0) {
                    res[i] = bump;
                    freq[bump - 'a']--;
                    fillSorted(res, i + 1, freq);
                    return new String(res);
                } else {
                    i--;
                    while (i >= 0) {
                        freq = savedFreqs[i];
                        bump = findBump(freq, target.charAt(i));
                        if (bump != 0) {
                            res[i] = bump;
                            freq[bump - 'a']--;
                            fillSorted(res, i + 1, freq);
                            return new String(res);
                        }
                        i--;
                    }
                    return "";
                }
            }
        }

        i = n - 1;
        while (i >= 0) {
            freq = savedFreqs[i];
            char bump = findBump(freq, target.charAt(i));
            if (bump != 0) {
                res[i] = bump;
                freq[bump - 'a']--;
                fillSorted(res, i + 1, freq);
                return new String(res);
            }
            i--;
        }
        return "";
    }

    char findBump(int[] freq, char t) {
        for (char c = (char)(t + 1); c <= 'z'; c++)
            if (freq[c - 'a'] > 0) return c;
        return 0;
    }

    void fillSorted(char[] res, int start, int[] freq) {
        int idx = start;
        for (int c = 0; c < 26; c++)
            for (int j = 0; j < freq[c]; j++)
                res[idx++] = (char)('a' + c);
    }
}