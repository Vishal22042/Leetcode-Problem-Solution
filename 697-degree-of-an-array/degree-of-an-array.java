class Solution {
    public int findShortestSubArray(int[] nums) {
        int[] count = new int[50000];
        int[] first = new int[50000];
        int[] last = new int[50000];

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            count[x]++;

            if (count[x] == 1) {
                first[x] = i;
            }

            last[x] = i;
        }

        int degree = 0;
        
        for (int x : nums) {
            degree = Math.max(degree, count[x]);
        }

        int answer = nums.length;

        for (int x : nums) {
            if (count[x] == degree) {
                answer = Math.min(answer, last[x] - first[x] + 1);
            }
        }

        return answer;
    }
}