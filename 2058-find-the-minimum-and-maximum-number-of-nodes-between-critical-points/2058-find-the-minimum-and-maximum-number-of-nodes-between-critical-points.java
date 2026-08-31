/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> crits = new ArrayList<>();
        ListNode prev = head, curr = head.next;
        int idx = 1;
        while (curr.next != null) {
            if ((curr.val > prev.val && curr.val > curr.next.val) || (curr.val < prev.val && curr.val < curr.next.val)) crits.add(idx);

            prev = curr;
            curr = curr.next;
            idx++;
        }
        if (crits.size() < 2) return new int[]{-1, -1};
        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i < crits.size(); i++)
            minDist = Math.min(minDist, crits.get(i) - crits.get(i-1));
        int maxDist = crits.get(crits.size()-1) - crits.get(0);
        return new int[]{minDist, maxDist};
    }
}