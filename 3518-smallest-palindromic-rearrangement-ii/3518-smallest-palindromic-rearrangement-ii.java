class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        char mid = 0;
        int[] half = new int[26];
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) mid = (char) ('a' + i);
            half[i] = freq[i] / 2;
        }

        int halfLen = s.length() / 2;

        long total = multinomial(halfLen, half, k);
        if (total < k) return "";

        StringBuilder sb = new StringBuilder();
        int remaining = halfLen;

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;
                half[c]--;
                remaining--;
                long perms = multinomial(remaining, half, k);

                if (perms >= k) {
                    sb.append((char) ('a' + c));
                    break;
                } else {
                    k -= perms;
                    half[c]++;
                    remaining++;
                }
            }
        }

        if (sb.length() < halfLen) return "";

        String h = sb.toString();
        String rev = new StringBuilder(h).reverse().toString();
        return mid == 0 ? h + rev : h + mid + rev;
    }

    long multinomial(int n, int[] counts, long cap) {
        long result = 1;
        int total = 0;
        for (int c = 0; c < 26; c++) {
            for (int j = 1; j <= counts[c]; j++) {
                total++;
                result = result * total / j;
                if (result > cap) return cap + 1;
            }
        }
        return result;
    }
}