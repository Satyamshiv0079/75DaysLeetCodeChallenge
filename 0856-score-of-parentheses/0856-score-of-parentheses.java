class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(0);
            else {
                int top = stack.pop();
                stack.push(stack.pop() + Math.max(2 * top, 1));
            }
        }
        return stack.pop();
    }
}