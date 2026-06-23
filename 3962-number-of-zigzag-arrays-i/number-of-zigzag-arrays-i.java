class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007;

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        long[] inc = new long[m];
        long[] dec = new long[m];

        for (int i = 0; i < m; i++) {
            inc[i] = 1;
            dec[i] = 1;
        }

        for (int len = 2; len <= n; len++) {

            long[] newInc = new long[m];
            long[] newDec = new long[m];

            long[] prefixInc = new long[m];
            long[] prefixDec = new long[m];

            prefixInc[0] = inc[0];
            prefixDec[0] = dec[0];

            for (int i = 1; i < m; i++) {
                prefixInc[i] = (prefixInc[i - 1] + inc[i]) % MOD;
                prefixDec[i] = (prefixDec[i - 1] + dec[i]) % MOD;
            }

            long totalInc = prefixInc[m - 1];
            long totalDec = prefixDec[m - 1];

            for (int v = 0; v < m; v++) {

                long lessDec = (v > 0) ? prefixDec[v - 1] : 0;
                long greaterInc = (totalInc - prefixInc[v] + MOD) % MOD;

                newInc[v] = lessDec;
                newDec[v] = greaterInc;
            }

            inc = newInc;
            dec = newDec;
        }

        long ans = 0;

        for (int i = 0; i < m; i++) {
            ans = (ans + inc[i] + dec[i]) % MOD;
        }

        return (int) ans;
    }
}