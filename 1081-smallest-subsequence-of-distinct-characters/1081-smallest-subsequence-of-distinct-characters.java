class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }

        boolean[] inStack = new boolean[26];
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int ci = c - 'a';
            if (inStack[ci]) continue;
            while (!stack.isEmpty() && stack.peek() > c && last[stack.peek() - 'a'] > i) {
                inStack[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            inStack[ci] = true;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        return sb.reverse().toString();
    }
}