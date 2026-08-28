class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        
        int odd = 0; char midChar = 0;
        for (int i = 0; i < 26; i++) if (freq[i] % 2 == 1) { odd++; midChar = (char)('a' + i); }
        if (n % 2 == 0 && odd > 0) return "";
        if (n % 2 == 1 && odd != 1) return "";

        
        int[] hfreq = new int[26];
        for (int i = 0; i < 26; i++) hfreq[i] = freq[i] / 2;

        int half = n / 2;
        String targetHalf = target.substring(0, half);

        char[] res = new char[half];
        int[][] saved = new int[half][26];
        int i = 0;

        while (i < half) {
            char t = targetHalf.charAt(i);
            if (hfreq[t - 'a'] > 0) {
                saved[i] = hfreq.clone();
                res[i] = t;
                hfreq[t - 'a']--;
                i++;
            } else {
                char bump = findBump(hfreq, t);
                if (bump != 0) {
                    res[i] = bump; hfreq[bump - 'a']--;
                    fillSorted(res, i + 1, hfreq);
                    return buildPalin(res, half, midChar, n, target);
                } else {
                    i--;
                    while (i >= 0) {
                        hfreq = saved[i];
                        bump = findBump(hfreq, targetHalf.charAt(i));
                        if (bump != 0) {
                            res[i] = bump; hfreq[bump - 'a']--;
                            fillSorted(res, i + 1, hfreq);
                            return buildPalin(res, half, midChar, n, target);
                        }
                        i--;
                    }
                    return "";
                }
            }
        }

        
        String candidate = buildPalinDirect(res, half, midChar, n);
        if (candidate.compareTo(target) > 0) return candidate;

        
        i = half - 1;
        while (i >= 0) {
            hfreq = saved[i];
            char bump = findBump(hfreq, targetHalf.charAt(i));
            if (bump != 0) {
                res[i] = bump; hfreq[bump - 'a']--;
                fillSorted(res, i + 1, hfreq);
                return buildPalin(res, half, midChar, n, target);
            }
            i--;
        }
        return "";
    }

    String buildPalin(char[] h, int half, char mid, int n, String target) {
        String candidate = buildPalinDirect(h, half, mid, n);
        return candidate.compareTo(target) > 0 ? candidate : "";
    }

    String buildPalinDirect(char[] h, int half, char mid, int n) {
        StringBuilder sb = new StringBuilder();
        for (char c : h) sb.append(c);
        if (n % 2 == 1) sb.append(mid);
        for (int j = half - 1; j >= 0; j--) sb.append(h[j]);
        return sb.toString();
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