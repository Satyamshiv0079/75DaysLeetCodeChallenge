class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        List<String> ans = new ArrayList<>();

        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int curr = s.charAt(i) - 'a';

                if (first[curr] < start) {
                    valid = false;
                    break;
                }

                end = Math.max(end, last[curr]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        int prevEnd = -1;

        for (int[] interval : intervals) {
            if (interval[0] > prevEnd) {
                ans.add(s.substring(interval[0], interval[1] + 1));
                prevEnd = interval[1];
            }
        }

        return ans;
    }
}