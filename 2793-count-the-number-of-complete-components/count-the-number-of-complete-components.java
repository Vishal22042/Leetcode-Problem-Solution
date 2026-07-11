class Solution {
    private int nodes;
    private int degreeSum;

    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        boolean[] visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                nodes = 0;
                degreeSum = 0;

                dfs(i, graph, visited);

                int edgeCount = degreeSum / 2;
                int expected = nodes * (nodes - 1) / 2;

                if (edgeCount == expected) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int u, List<Integer>[] graph, boolean[] visited) {
        visited[u] = true;
        nodes++;
        degreeSum += graph[u].size();

        for (int v : graph[u]) {
            if (!visited[v]) {
                dfs(v, graph, visited);
            }
        }
    }
}