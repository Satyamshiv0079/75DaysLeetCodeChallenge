class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        StringBuilder half = new StringBuilder();
        char mid = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) mid = (char) ('a' + i);
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) ('a' + i));
            }
        }
        String halfStr = half.toString();
        String reversed = half.reverse().toString();
        return mid == 0 ? halfStr + reversed : halfStr + mid + reversed;
    }
}