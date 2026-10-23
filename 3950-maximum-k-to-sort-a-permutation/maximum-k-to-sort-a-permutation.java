
class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                k |= nums[i];
            }
        }

        if (k == 0) {
            return 0;
        }

        int ans = k;
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) {
                ans &= nums[i];
            }
        }

        return ans;
    }
}
