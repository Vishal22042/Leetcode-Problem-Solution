import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int distance = road[2];
            
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new int[]{v, distance});
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new int[]{u, distance});
        }
        
       
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        
        queue.offer(1);
        visited[1] = true;
        
        int minScore = Integer.MAX_VALUE;
        
        while (!queue.isEmpty()) {
            int u = queue.poll();
            
            if (!graph.containsKey(u)) continue;
            
            for (int[] neighbor : graph.get(u)) {
                int v = neighbor[0];
                int distance = neighbor[1];
                
                
                minScore = Math.min(minScore, distance);
                
               
                if (!visited[v]) {
                    visited[v] = true;
                    queue.offer(v);
                }
            }
        }
        
        return minScore;
    }
}
