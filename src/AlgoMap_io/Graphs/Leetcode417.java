package AlgoMap_io.Graphs;

import java.util.*;

/*
There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
The Pacific Ocean touches the island's left and top edges,
and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells.
You are given an m x n integer matrix heights where heights[r][c]
represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain,
and the rain water can flow to neighboring cells directly north, south, east, and west
if the neighboring cell's height is less than or equal to the current cell's height.
Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result
where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 */
public class Leetcode417{
    public static void main(String[] args) {

    }
    static int m,n;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        m = heights.length; n = heights[0].length;
        //pacific가능, atlantic 가능 따로 출력
        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];
        //따로 BFS
        Queue<int[]> pacificQueue = new LinkedList<>();
        Queue<int[]> atlanticQueue = new LinkedList<>();

        //이걸 구현하는게 더 어렵네...
        for(int i=0;i<m;i++){
            pacificQueue.add(new int[]{i,0});
            pacific[i][0] = true;
        }
        for(int i=0;i<n;i++){
            pacificQueue.add(new int[]{0,i});
            pacific[0][i] = true;
        }

        for(int i=0;i<m;i++){
            atlanticQueue.add(new int[]{i,n-1});
            atlantic[i][n-1] = true;
        }
        for(int i=0;i<n;i++){
            atlanticQueue.add(new int[]{m-1,i});
            atlantic[m-1][i] = true;
        }

        bfsWaterFlow(pacificQueue,pacific,heights);
        bfsWaterFlow(atlanticQueue,atlantic,heights);

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(pacific[i][j]&&atlantic[i][j]){
                    //둘다 도달 가능한 지점들을 최종 리스트에 추가
                    ans.add(List.of(i,j));
                }
            }
        }
        return ans;
    }

    private static void bfsWaterFlow(Queue<int[]> queue, boolean[][] ocean, int[][] heights) {
        int[][] movements = {{1,0},{-1,0},{0,1},{0,-1}};
        while(!queue.isEmpty()){
            int[] island = queue.poll();
            for(int[] movement : movements){
                int newX = island[0]+movement[0];
                int newY = island[1]+movement[1];
                //바다 바로 옆에서 역으로 올라갈 수 있는 경우의 수를 탐색해야 함.
                if(isSafe(newX,newY)&&!ocean[newX][newY]&&heights[newX][newY]>=heights[island[0]][island[1]]){
                    queue.add(new int[]{newX,newY});
                    //배열은 참조를 받으니까 변경할 수 있음.
                    ocean[newX][newY]=true;
                }
            }
        }
    }
    public static boolean isSafe(int x, int y){
        return x>=0&&x<m&&y>=0&&y<n;
    }

    //DFS로 푸는 방법 - 실행시간 4초
    //탐색 방식이 단방향이고, 가지 수가 제한적인 상황 - DFS가 더 빠름
    static int rows, cols;
    static boolean[][] pacific, atlantic;
    static List<List<Integer>> ans;
    public List<List<Integer>> pacificAtlanticDFS(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;
        pacific = new boolean[rows][cols];
        atlantic = new boolean[rows][cols];
        ans = new ArrayList<>(); // 1

        // 2
        for(int col = 0; col < cols; col++){
            dfsWaterFlow(0, col, pacific, heights);
            dfsWaterFlow(rows - 1, col, atlantic, heights);
        }
        for(int row = 0; row < rows; row++){
            dfsWaterFlow(row, 0, pacific, heights);
            dfsWaterFlow(row, cols - 1, atlantic, heights);
        }
        return ans;
    }

    public void dfsWaterFlow(int row, int col, boolean[][] visited, int[][] heights){
        if(visited[row][col]) return;
        visited[row][col] = true;
        if(pacific[row][col] && atlantic[row][col]) ans.add(Arrays.asList(row,col));
        if(row + 1 < rows && heights[row + 1][col] >= heights[row][col])
            dfsWaterFlow(row + 1, col, visited, heights);
        if(row - 1 >= 0 && heights[row - 1][col] >= heights[row][col])
            dfsWaterFlow(row - 1, col, visited, heights);
        if(col + 1 < cols && heights[row][col + 1] >= heights[row][col])
            dfsWaterFlow(row, col + 1, visited, heights);
        if(col - 1 >= 0 && heights[row][col - 1] >= heights[row][col])
            dfsWaterFlow(row, col - 1, visited, heights);
    }
}
/*
각 셀에 비가 떨어졌을 때 양 바다 모두에 도달할 수 있냐?
각 셀을 그래프의 노드라고 보면, 각 노드를 기준으로,
고도에 맞게 (연결 여부를 대신한다) bfs 탐색을 했을 때,
맨 끝에 도달하는지를 보고 싶은 것이다.

여기서의 아이디어는 바다 바로 옆 노드에서 오히려 역으로 물이 어디까지 올라가는지 탐색한다는 것이다.
셀 기준으로 BFS를 하면 매번 visited 체크와 기록이 복잡하다.
바다에서부터 역으로 높이가 같거나 더 높은 곳만 탐색하면, 해당 셀로부터 바다까지 흐를 수 있다는 뜻이 된다.
 */