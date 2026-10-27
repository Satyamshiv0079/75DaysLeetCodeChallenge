class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];
        for (char[] row : grid) Arrays.fill(row, '#');
        for (int j = 0; j < n; j++) grid[0][j] = '.';
        for (int i = 1; i < m; i++) grid[i][n-1] = '.';
        return Arrays.stream(grid).map(String::new).toArray(String[]::new);
    }
}