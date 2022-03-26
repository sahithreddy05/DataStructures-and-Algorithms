public class DFSQuestions {

    // 200 leetcode
    public void dfs_numIslands(char[][] grid, int i, int j, int[][] dir) {
        int n = grid.length, m = grid[0].length;

        grid[i][j] = '0';

        for (int[] d : dir) {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == '1') {
                dfs_numIslands(grid, r, c, dir);
            }

        }
    }

    public int numIslands(char[][] grid) {

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length, components = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    components++;
                    dfs_numIslands(grid, i, j, dir);
                }
            }
        }
        return components;
    }

    // 695

    public int dfs_maxAreaOfIsland(int[][] grid, int i, int j, int[][] dir) {

        int n = grid.length, m = grid[0].length, size = 0;
        grid[i][j] = 0;

        for (int[] d : dir) {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                size += dfs_maxAreaOfIsland(grid, r, c, dir);
            }
        }

        return size + 1;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
        int n = grid.length, m = grid[0].length, maxArea = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs_maxAreaOfIsland(grid, i, j, dir);
                    maxArea = Math.max(area, maxArea);
                }
            }
        }
        return maxArea;
    }

    // 463
    public int islandPerimeter(int[][] grid) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = grid.length, m = grid[0].length, nbrcount = 0, onceCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    onceCount++;
                    for (int d = 0; d < dir.length; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1)
                            nbrcount++;
                    }
                }
            }
        }
        return 4 * onceCount - nbrcount;
    }

    // 130

    void dfs_surrounded(char[][] grid, int i, int j, int[][] dir) {

        int n = grid.length, m = grid[0].length;
        grid[i][j] = '$';
        for (int[] d : dir) {
            int r = i + d[0];
            int c = j + d[1];

            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 'O') {
                dfs_surrounded(grid, r, c, dir);
            }
        }
    }

    void solve(char[][] board) {
        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int n = board.length, m = board[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    if (board[i][j] == 'O')
                        dfs_surrounded(board, i, j, dir);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '$')
                    board[i][j] = 'O';
            }
        }
    }
}
