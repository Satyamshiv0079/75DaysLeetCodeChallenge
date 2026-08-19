class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        for (int[] r : reservedSeats) {
            int row = r[0], seat = r[1];
            if (seat < 2 || seat > 9) continue;
            int bit = 1 << (seat - 2);
            rowMask.merge(row, bit, (a, b) -> a | b);
        }

        int leftBlock = 0b00001111;
        int midBlock  = 0b00111100;
        int rightBlock= 0b11110000;

        long total = (long) (n - rowMask.size()) * 2;

        for (int mask : rowMask.values()) {
            if ((mask & leftBlock) == 0 && (mask & rightBlock) == 0) {
                total += 2;
            }
            else if ((mask & leftBlock) == 0 || (mask & midBlock) == 0 || (mask & rightBlock) == 0) {
                total += 1;
            }
        }
        return (int) total;
    }
}