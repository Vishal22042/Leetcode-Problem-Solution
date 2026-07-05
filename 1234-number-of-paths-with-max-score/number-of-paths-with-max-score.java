import java.util.List;
import java.util.Arrays;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;
    
        int[][] dp = new int[n][n];
        int[][] count = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        dp[n - 1][n - 1] = 0;
        count[n - 1][n - 1] = 1;
        
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        
        for (int i = n - 1; i >= 0; i--) {
            String row = board.get(i);
            for (int j = n - 1; j >= 0; j--) {
                char cell = row.charAt(j);

                if (cell == 'X' || cell == 'S') {
                    continue;
                }
                
                int maxScore = -1;
                int maxCount = 0;
                
                for (int[] dir : dirs) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    
                    if (ni < n && nj < n && dp[ni][nj] != -1) {
                        if (dp[ni][nj] > maxScore) {
                            maxScore = dp[ni][nj];
                            maxCount = count[ni][nj];
                        } else if (dp[ni][nj] == maxScore) {
                            maxCount = (maxCount + count[ni][nj]) % MOD;
                        }
                    }
                }
                
                if (maxScore != -1) {
                    dp[i][j] = maxScore;
                    if (cell != 'E') {
                        dp[i][j] += (cell - '0');
                    }
                    count[i][j] = maxCount;
                }
            }
        }
        
        if (dp[0][0] == -1) {
            return new int[]{0, 0};
        }
        
        return new int[]{dp[0][0], count[0][0]};
    }
}
