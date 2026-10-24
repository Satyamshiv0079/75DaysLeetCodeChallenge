class Solution {
    int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int start = Math.max(toDayOfYear(arriveAlice), toDayOfYear(arriveBob));
        int end = Math.min(toDayOfYear(leaveAlice), toDayOfYear(leaveBob));
        return Math.max(0, end - start + 1);
    }
    int toDayOfYear(String date) {
        int m = Integer.parseInt(date.substring(0, 2));
        int d = Integer.parseInt(date.substring(3));
        int total = d;
        for (int i = 0; i < m  - 1; i++) {
            total += days[i];
        }
        return total;
    }
}