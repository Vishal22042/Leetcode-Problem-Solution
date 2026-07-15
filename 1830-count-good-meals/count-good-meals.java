class Solution {
    public int countPairs(int[] deliciousness) {
        int MOD = 1_000_000_007;
        HashMap<Integer, Integer> map = new HashMap<>();
        long ans = 0;

        int max = 0;
        for (int val : deliciousness) {
            max = Math.max(max, val);
        }

        int maxSum = max * 2;

        for (int val : deliciousness) {
            int power = 1;
            while (power <= maxSum) {
                int need = power - val;
                ans = (ans + map.getOrDefault(need, 0)) % MOD;
                power <<= 1;
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }

        return (int) ans;
    }
}