class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        long[] digitSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            digitSum[i + 1] = digitSum[i] + (s.charAt(i) - '0');
        }
        int[] nzPos = new int[n];
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                nzPos[m++] = i;
            }
        }

        long[] pow10 = new long[m + 1];
        pow10[0] = 1;
        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // hash of non-zero digit sequence
        long[] pref = new long[m + 1];
        for (int i = 0; i < m; i++) {
            int d = s.charAt(nzPos[i]) - '0';
            pref[i + 1] = (pref[i] * 10 + d) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            long sum = digitSum[r + 1] - digitSum[l];

            int left = lowerBound(nzPos, m, l);
            int right = upperBound(nzPos, m, r);

            long x = 0;
            if (left < right) {
                int len = right - left;
                x = (pref[right]
                        - pref[left] * pow10[len] % MOD
                        + MOD) % MOD;
            }

            ans[i] = (int) (x * (sum % MOD) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] a, int n, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }

    private int upperBound(int[] a, int n, int target) {
        int l = 0, r = n;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (a[mid] <= target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}