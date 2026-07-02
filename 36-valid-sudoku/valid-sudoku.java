class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rowSeen = new boolean[9][9];
        boolean[][] colSeen = new boolean[9][9];
        boolean[][] boxSeen = new boolean[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val == '.') continue;
                
                int num = val - '1';
                int boxIndex = (r / 3) * 3 + (c / 3);

                if (rowSeen[r][num] || colSeen[c][num] || boxSeen[boxIndex][num]) {
                    return false;
                }

                rowSeen[r][num] = true;
                colSeen[c][num] = true;
                boxSeen[boxIndex][num] = true;
            }
        }
        return true;
    }
}
