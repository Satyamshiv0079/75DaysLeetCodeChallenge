class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {

        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (board[r][c] == '.') {

                    
                    for (char num = '1'; num <= '9'; num++) {

                        if (isValid(board, r, c, num)) {

                            
                            board[r][c] = num;

                            
                            if (solve(board)) {
                                return true;
                            }

                            
                            board[r][c] = '.';
                        }
                    }

                    
                    return false;
                }
            }
        }

        
        return true;
    }

    private boolean isValid(char[][] board, int row, int col, char num) {

        
        for (int c = 0; c < 9; c++) {
            if (board[row][c] == num) {
                return false;
            }
        }

        
        for (int r = 0; r < 9; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }

        
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;

        for (int r = startRow; r < startRow + 3; r++) {
            for (int c = startCol; c < startCol + 3; c++) {
                if (board[r][c] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}