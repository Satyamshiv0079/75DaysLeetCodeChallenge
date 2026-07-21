class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int total1s = 0;
        for (char c : s.toCharArray()) if (c == '1') total1s++;

        List<int[]> runs = new ArrayList<>();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = i;
            while (j < n && s.charAt(j) == c) j++;
            runs.add(new int[]{c - '0', j - i});
            i = j; 
        }
        int maxGain = 0;
        for (int k = 1; k < runs.size() - 1; k++) {
            if (runs.get(k)[0] == 1 && runs.get(k-1)[0] == 0 && runs.get(k+1)[0] == 0) {
                int gain = runs.get(k-1)[1] + runs.get(k+1)[1];
                maxGain = Math.max(maxGain, gain);
            }
        }
        return total1s + maxGain;
    }
}