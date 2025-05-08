package AlgoMap_io.Graphs;

import java.util.LinkedList;
import java.util.Queue;
/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
You may assume all four edges of the grid are all surrounded by water.
 */
public class Leetcode200 {
    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(numIslands(grid));
    }
    static int ans;
    static int[][] movement = {{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static int M,N;
    public static int numIslands(char[][] grid) {
        ans = 0;
        M = grid.length; N = grid[0].length;
        visited = new boolean[grid.length][grid[0].length];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(grid[i][j]=='1'&&!visited[i][j]){
                    bfsIsland(grid,i,j);
                    ans++;
                }
            }
        }
        return ans;
    }
    public static void bfsIsland(char[][] grid, int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y]=true;
        queue.offer(new int[]{x,y});
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            for(int k=0;k<4;k++){
                int i = point[0]+movement[k][0];
                int j = point[1]+movement[k][1];
                if(isSafe(i,j)&&grid[i][j]=='1'&&!visited[i][j]){
                    visited[i][j]=true;
                    queue.offer(new int[]{i,j});
                }
            }
        }
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<M&&y>=0&&y<N;
    }
    //algomap.io dfs solution - 실행시간 2ms
    public int numIslandsDFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int numIslands = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numIslands;
    }

    private void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') return;
        grid[i][j] = '0';
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
    }
}
