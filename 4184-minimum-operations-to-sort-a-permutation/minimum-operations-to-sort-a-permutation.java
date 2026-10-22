class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int pos = 0;

        while (nums[pos] != 0) {
            pos++;
        }

        boolean normal = true;
        boolean reversed = true;

        for (int i = 0; i < n; i++) {
            if (nums[(pos + i) % n] != i) {
                normal = false;
            }

            if (nums[(pos - i + n) % n] != i) {
                reversed = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (normal) {
            ans = Math.min(pos, pos == 0 ? 0 : n - pos + 2);
        }

        if (reversed) {
            int k = (pos + 1) % n;
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}