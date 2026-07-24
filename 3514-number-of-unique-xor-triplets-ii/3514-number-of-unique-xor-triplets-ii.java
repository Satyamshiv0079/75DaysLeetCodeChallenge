class Solution {
    public int uniqueXorTriplets(int[] nums) {
        
        int n = nums.length;
        int maxVal = 2048;
        boolean[] pairXors = new boolean[maxVal];
        boolean[] seen = new boolean[maxVal];

        for (int j = 0; j < n; j++) {
            for (int k = j; k < n; k++) {
                pairXors[nums[j] ^ nums[k]] = true;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int x = 0; x < maxVal; x++) {
                if (pairXors[x]) seen[nums[i] ^ x] = true;
            }
        }

        int count = 0;
        for (boolean b : seen) if (b) count++;
        return count;

    }
}