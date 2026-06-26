class Solution {

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int offset = n + 1;
        int[] bit = new int[2 * n + 2];

        long totalSubarrays = 0;
        int currentPrefixSum = 0;

        update(bit, currentPrefixSum + offset, 1);

        for (int num : nums) {
            currentPrefixSum += (num == target) ? 1 : -1;
            totalSubarrays += query(bit, currentPrefixSum + offset - 1);
            update(bit, currentPrefixSum + offset, 1);
        }

        return totalSubarrays;
    }

    private void update(int[] bit, int idx, int val) {
        while (idx < bit.length) {
            bit[idx] += val;
            idx += idx & (-idx);
        }
    }

    private int query(int[] bit, int idx) {
        int sum = 0;
        while (idx > 0) {
            sum += bit[idx];
            idx -= idx & (-idx);
        }
        return sum;
    }
}