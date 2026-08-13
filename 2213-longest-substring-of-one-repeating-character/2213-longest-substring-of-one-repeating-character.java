class Solution {
    int[] maxLen, prefLen, sufLen, prefChar, sufChar, len;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        char[] arr = s.toCharArray();
        maxLen = new int[4 * n];
        prefLen = new int[4 * n];
        sufLen = new int[4 * n];
        prefChar = new int[4 * n];
        sufChar = new int[4 * n];
        len = new int[4 * n];
        build(arr, 1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            arr[queryIndices[i]] = queryCharacters.charAt(i);
            update(arr, 1, 0, n - 1, queryIndices[i]);
            ans[i] = maxLen[1];
        }
        return ans;
    }
    void build(char[] arr, int node, int l, int r) {
        len[node] = r - l + 1;
        if (l == r) {
            maxLen[node] = prefLen[node] = sufLen[node] = 1;
            prefChar[node] = sufChar[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(arr, 2*node, l, mid);
        build(arr, 2*node+1, mid+1, r);
        merge(node);
    }
    void update(char[] arr, int node, int l, int r, int idx) {
        if (l == r) {
            prefChar[node] = sufChar[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(arr, 2*node, l, mid, idx);
        else update(arr, 2*node+1, mid+1, r, idx);
        merge(node);
    }
    void merge(int node) {
        int L = 2*node, R = 2*node+1;
        prefChar[node] = prefChar[L];
        sufChar[node] = sufChar[R];
        len[node] = len[L] + len[R];

        prefLen[node] = prefLen[L];
        if (sufChar[L] == prefChar[R] && prefLen[L] == len[L])
            prefLen[node] = len[L] + prefLen[R];

        sufLen[node] = sufLen[R];
        if (sufChar[L] == prefChar[R] && sufLen[R] == len[R])
            sufLen[node] = len[R] + sufLen[L];

        maxLen[node] = Math.max(maxLen[L], maxLen[R]);
        if (sufChar[L] == prefChar[R])
            maxLen[node] = Math.max(maxLen[node], sufLen[L] + prefLen[R]);
    }
}