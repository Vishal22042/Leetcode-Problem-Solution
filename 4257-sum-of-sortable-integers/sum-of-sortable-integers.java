class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n; start += k) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                int drops = 0;

                for (int j = start; j < start + k; j++) {
                    min = Math.min(min, nums[j]);
                    max = Math.max(max, nums[j]);

                    if (j > start && nums[j] < nums[j - 1]) {
                        drops++;
                    }
                }

                if (drops > 1 ||
                    (drops == 1 && nums[start] < nums[start + k - 1]) ||
                    min < prevMax) {
                    valid = false;
                    break;
                }

                prevMax = max;
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}