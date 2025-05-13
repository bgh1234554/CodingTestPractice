package AlgoMap_io.Graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/*
You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.
If this is impossible, return -1.
 */
public class Leetcode994 {
    public static void main(String[] args) {
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}}));
        System.out.println(orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 1}, {0, 1, 2}}));
        System.out.println(orangesRotting(new int[][]{{0}}));
    }
    static int m,n;
    public static int orangesRotting(int[][] grid) {
        m = grid.length; n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
        Queue<int[]> queue = new LinkedList<>();
        int days = -1; int numFresh = 0;
        //처음에 썩어있는 사과를 거낼 때 0일차부터 시작하기 때문에
        //-1로 초기화해야한다.
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    queue.offer(new int[]{i,j});
                }
                else if(grid[i][j]==1){
                    numFresh++;
                }
            }
        }
        //3번째 케이스 - 만약 싱싱한 오렌지가 없다면 이미 다 썩어있거나 오렌지가 없는 것이므로 0을 리턴한다.
        if(numFresh==0) return 0;

        //BFS로 탐색, 한바퀴
        while(!queue.isEmpty()){
            //한번에 큐에 들어있는 썩은 사과가 전염시켜야 하루가 지나가니까
            //for문을 하나 더 만들어야 계산할 수 있다.
            int size = queue.size();
            days++;
            for(int k=0;k<size;k++){
                int[] point = queue.poll();
                int row = point[0];
                int col = point[1];
                for (int[] dir : direction) {
                    int x = row + dir[0];
                    int y = col + dir[1];
                    if (isSafe(x, y) && grid[x][y] == 1 && !visited[x][y]) {
                        grid[x][y] = 2;
                        numFresh--;
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
        }
//        //살펴보고 안썩은게 있으면 -1 리턴
//        for(int i=0;i<m;i++){
//            for(int j=0;j<n;j++){
//                if(grid[i][j]==1){
//                    return -1;
//                }
//            }
//        }
        return numFresh==0 ? days : -1;
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }
}
/*
BFS로 구현해야겠다는 아이디어와 탐색 로직 구현자체는 매우 전형적인데,
오히려 날짜를 세는 방법을 생각해내는 것이 더 어려운 것 같다.
 */