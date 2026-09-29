class Solution:
    def hasValidPath(self, grid):
        m = len(grid)
        n = len(grid[0])

        if (m + n - 1) % 2 != 0:
            return False

        if grid[0][0] == ')':
            return False

        dp = [[set() for _ in range(n)] for _ in range(m)]

        dp[0][0].add(1)

        for i in range(m):
            for j in range(n):

                if i == 0 and j == 0:
                    continue

                if i > 0:
                    dp[i][j].update(dp[i - 1][j])

                if j > 0:
                    dp[i][j].update(dp[i][j - 1])

                new_balances = set()

                for balance in dp[i][j]:
                    if grid[i][j] == '(':
                        new_balance = balance + 1
                    else:
                        new_balance = balance - 1

                    if new_balance >= 0:
                        new_balances.add(new_balance)

                dp[i][j] = new_balances

        return 0 in dp[m - 1][n - 1]