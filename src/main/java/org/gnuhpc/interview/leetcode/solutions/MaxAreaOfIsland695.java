package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.datastructure.unionfind.QuickUnion;
import org.junit.Test;

/**
 * Copyright gnuhpc 2019/9/22
 */
public class MaxAreaOfIsland695 {
    //二维DFS 模板
    private int[][] dr = {
            {-1, 0},
            {0, 1},
            {1, 0},
            {0, -1}
    };
    private int r;
    private int c;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        r = grid.length;
        c = grid[0].length;
        int max = 0;

        visited = new boolean[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                max = Math.max(max, dfs(grid, i, j, visited));
            }
        }

        return max;
    }

    private int dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (grid[x][y] != 1) return 0;

        visited[x][y] = true;
        int res = 1;
        for (int i = 0; i < dr.length; i++) {
            int newX = x + dr[i][0];
            int newY = y + dr[i][1];
            if (isValid(grid, visited, newX, newY)) {
                res += dfs(grid, newX, newY, visited);
            }
        }

        return res;
    }

    private boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
        return !(x == -1 || x == grid.length || y == -1 ||
                y == grid[0].length || visited[x][y] || grid[x][y] == 0);
    }

    /*
    Method2: 并查集
     */

    public int maxAreaOfIsland2(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int r = grid.length;
        int c = grid[0].length;
        QuickUnion qu = new QuickUnion(r * c);
        boolean isAllZero = true;
        for (int x = 0; isAllZero && x < r; x++) {
            for (int y = 0; isAllZero && y < c; y++) {
                if (grid[x][y] != 0) {
                    isAllZero = false;
                }
            }
        }

        if (isAllZero) return 0;

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                if (grid[x][y] == 1) {
                    //四个方向进行操作
                    for (int k = 0; k < 4; k++) {
                        int newX = x + dr[k][0];
                        int newY = y + dr[k][1];

                        if (newX >= 0 && newX < r && newY >= 0 && newY < c) {
                            if (grid[newX][newY] == 1) {
                                qu.union(x * c + y, newX * c + newY);
                            }
                        }
                    }
                }
            }
        }

        return qu.maxCount();
    }

    /*
    Method3: 全局变量+DFS
     */

    private int res = 0;
    private int cur = 0;

    public int maxAreaOfIsland3(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    cur = 0;
                    dfs(grid, i, j);
                    res = Math.max(res, cur);
                }
            }
        }
        return res;
    }

    void dfs(int[][] grid, int i, int j) {
        if (i != -1 && i != grid.length && j != -1 && j != grid[0].length && grid[i][j] == 1) {
            cur++;
            grid[i][j] = 0;
            dfs(grid, i - 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i + 1, j);
            dfs(grid, i, j + 1);
        }
    }

    @Test
    public void test() {
        System.out.println(maxAreaOfIsland2(new int[][]{
                {1, 1, 0},
                {0, 1, 1},
                {1, 1, 1}
        }));
        System.out.println(maxAreaOfIsland2(new int[][]{
                {0}
        }));
        System.out.println(maxAreaOfIsland2(new int[][]{
                {1, 0},
                {1, 0},
                {1, 1}
        }));
    }
}
