class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        List<int[]> runs = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            runs.add(new int[]{s.charAt(i) - '0', i, j - 1});
            i = j;
        }
        int m = runs.size();

        int totalOnes = 0;
        for (char c : s.toCharArray()) if (c == '1') totalOnes++;

        int[] runStart = new int[m], runEnd = new int[m], runType = new int[m], runLen = new int[m];
        for (int k = 0; k < m; k++) {
            runType[k] = runs.get(k)[0];
            runStart[k] = runs.get(k)[1];
            runEnd[k] = runs.get(k)[2];
            runLen[k] = runEnd[k] - runStart[k] + 1;
        }

        int[] gain = new int[m];
        for (int k = 1; k < m - 1; k++)
            if (runType[k] == 1 && runType[k-1] == 0 && runType[k+1] == 0)
                gain[k] = runLen[k-1] + runLen[k+1];

        int LOG = 17;
        int[][] sp = new int[LOG][m];
        sp[0] = gain.clone();
        for (int j = 1; j < LOG; j++)
            for (int k = 0; k + (1 << j) <= m; k++)
                sp[j][k] = Math.max(sp[j-1][k], sp[j-1][k + (1 << (j-1))]);

        List<Integer> ans = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0], r = q[1];
            int maxGain = 0;

            int runL = lowerBound(runEnd, l);
            int runR = upperBound(runStart, r) - 1;

            if (runL <= runR) {
                int innerL = (runStart[runL] < l) ? runL + 1 : runL;
                int innerR = (runEnd[runR] > r) ? runR - 1 : runR;

                int qlo = innerL + 1, qhi = innerR - 1;
                if (qlo <= qhi && qlo < m && qhi >= 0) {
                    qlo = Math.max(qlo, 0);
                    qhi = Math.min(qhi, m - 1);
                    int len = qhi - qlo + 1;
                    int log = 31 - Integer.numberOfLeadingZeros(len);
                    maxGain = Math.max(sp[log][qlo], sp[log][qhi - (1 << log) + 1]);
                }

                // Left partial 0-run
                if (runStart[runL] < l && runType[runL] == 0) {
                    int k = runL + 1;
                    if (k < m && k + 1 < m && runType[k] == 1 && runType[k+1] == 0 && runEnd[k+1] <= r) {
                        int leftPart = runEnd[runL] - l + 1;
                        maxGain = Math.max(maxGain, leftPart + runLen[k+1]);
                    }
                }

                // Right partial 0-run
                if (runEnd[runR] > r && runType[runR] == 0) {
                    int k = runR - 1;
                    if (k >= 0 && k - 1 >= 0 && runType[k] == 1 && runType[k-1] == 0 && runStart[k-1] >= l) {
                        int rightPart = r - runStart[runR] + 1;
                        maxGain = Math.max(maxGain, runLen[k-1] + rightPart);
                    }
                }

                // Both edges partial, single 1-run between them
                if (runL < m && runR >= 0 && runStart[runL] < l && runEnd[runR] > r
                    && runType[runL] == 0 && runType[runR] == 0
                    && runL + 1 == runR - 1 && runType[runL + 1] == 1) {
                    int leftPart = runEnd[runL] - l + 1;
                    int rightPart = r - runStart[runR] + 1;
                    maxGain = Math.max(maxGain, leftPart + rightPart);
                }
            }

            ans.add(totalOnes + maxGain);
        }
        return ans;
    }

    int lowerBound(int[] arr, int val) {
        int lo = 0, hi = arr.length - 1, res = arr.length;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] >= val) { res = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        return res;
    }

    int upperBound(int[] arr, int val) {
        int lo = 0, hi = arr.length - 1, res = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= val) { res = mid + 1; lo = mid + 1; }
            else hi = mid - 1;
        }
        return res;
    }
}