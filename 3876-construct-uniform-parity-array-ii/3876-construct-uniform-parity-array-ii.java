class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE, minEven = Integer.MAX_VALUE;
        for (int x : nums1) {
            if (x % 2 == 1) minOdd = Math.min(minOdd, x);
            else minEven = Math.min(minEven, x);
        }
        return canMakeOdd(nums1, minOdd) || canMakeEven(nums1, minOdd, minEven);
    }

    boolean canMakeOdd(int[] nums, int minOdd) {
        for (int x : nums) {
            if (x % 2 == 1) continue;
            
            if (minOdd == Integer.MAX_VALUE || minOdd >= x) return false;
        }
        return true;
    }

    boolean canMakeEven(int[] nums, int minOdd, int minEven) {
        for (int x : nums) {
            if (x % 2 == 0) continue;
            
            if (minOdd == Integer.MAX_VALUE || minOdd >= x) return false;
        }
        return true;
    }
}