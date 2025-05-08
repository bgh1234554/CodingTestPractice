package AlgoMap_io.Graphs;

import java.util.LinkedList;
import java.util.Queue;
/*
You are given an m x n binary matrix grid.
An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.
 */
public class Leetcode695 {
    public static void main(String[] args) {
        int[][] grid = {{0,0,1,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,1,1,0,1,0,0,0,0,0,0,0,0},{0,1,0,0,1,1,0,0,1,0,1,0,0},{0,1,0,0,1,1,0,0,1,1,1,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,0,0,1,1,1,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }
    static int ans;
    static int[][] movement = {{1,0},{-1,0},{0,1},{0,-1}};
    static boolean[][] visited;
    static int M,N;
    //실행시간 4ms
    public static int maxAreaOfIsland(int[][] grid){
        ans = 0;
        M = grid.length; N = grid[0].length;
        visited = new boolean[grid.length][grid[0].length];
        for(int i=0;i<M;i++){
            for(int j=0;j<N;j++){
                if(grid[i][j]==1&&!visited[i][j]){
                    bfsIsland(grid,i,j);
                }
            }
        }
        return ans;
    }
    public static void bfsIsland(int[][] grid, int x, int y){
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y]=true;
        queue.offer(new int[]{x,y});
        int count = 0;
        while(!queue.isEmpty()){
            int[] point = queue.poll();
            for(int k=0;k<4;k++){
                int i = point[0]+movement[k][0];
                int j = point[1]+movement[k][1];
                if(isSafe(i,j)&&grid[i][j]==1&&!visited[i][j]){
                    visited[i][j]=true;
                    queue.offer(new int[]{i,j});
                }
            }
            count++;
        }
        ans = Math.max(ans,count);
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<M&&y>=0&&y<N;
    }
    //algomap.io DFS - 실행시간 1ms
    public int maxAreaOfIslandDFS(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int maxArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, dfs(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 0; // Mark as visited
        int area = 1;
        area += dfs(grid, i + 1, j); // Down
        area += dfs(grid, i - 1, j); // Up
        area += dfs(grid, i, j + 1); // Right
        area += dfs(grid, i, j - 1); // Left
        return area;
    }
}
