class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] a = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0] = nums[i];
            a[i][1] = i;
        }

        Arrays.sort(a, (x, y) -> Integer.compare(x[0], y[0]));

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[a[i][1]] = i;
        }

        int[] next = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && a[r + 1][0] - a[i][0] <= maxDiff) {
                r++;
            }
            next[i] = r;
            if (r < i + 1) r = i + 1;
        }

        int LOG = 17;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = pos[queries[qi][0]];
            int v = pos[queries[qi][1]];

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            if (next[u] <= u) {
                ans[qi] = -1;
                continue;
            }

            int cur = u;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                int to = up[k][cur];
                if (to < v) {
                    steps += (1 << k);
                    cur = to;
                }
            }

            if (next[cur] < v) {
                ans[qi] = -1;
            } else {
                ans[qi] = steps + 1;
            }
        }

        return ans;
    }
}