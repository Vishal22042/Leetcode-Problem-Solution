class Solution {
    private static final int MOD = 1_000_000_007;

    public int numberOfSets(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= k; j++) {
            long prefix = 0;

            for (int i = 1; i <= n; i++) {

                dp[i][j] = dp[i - 1][j];

                if (i >= 2) {
                    prefix = (prefix + dp[i - 1][j - 1]) % MOD;
                    dp[i][j] = (int) ((dp[i][j] + prefix) % MOD);
                }
            }
        }

        return dp[n][k];
    }
}