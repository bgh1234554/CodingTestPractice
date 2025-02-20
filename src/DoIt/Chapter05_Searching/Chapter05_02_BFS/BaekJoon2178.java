package DoIt.Chapter05_Searching.Chapter05_02_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon2178 {
    static int[][] maze;
    static boolean[][] visited;
    static int N;
    static int M;
    static int[][] movement = {{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];
        for(int i=1;i<=N;i++){
            String str = br.readLine();
            for(int j=1;j<=M;j++){
                maze[i][j]=Integer.parseInt(String.valueOf(str.charAt(j-1)));
            }
        }
        bfsMaze(1,1);
        System.out.println(maze[N][M]);
    }
    public static void bfsMaze(int x, int y){
        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[]{x,y});
        visited[x][y]=true;
        while(!queue.isEmpty()){
            //부모노드 정보 저장
            int parentx = queue.peek()[0];
            int parenty = queue.peek()[1];
            queue.poll();
            //자식 노드가 무조건 4개. 상하좌우. 이전에 있던 for-each문의 역할을 하는 것이다.
            for (int k = 0; k < 4; k++) {
                int dx = parentx + movement[k][0];
                int dy = parenty + movement[k][1];
                //미로 바깥이면 안되니까 확인
                if (isSafe(dx, dy) && !visited[dx][dy] && maze[dx][dy] == 1) {
                    queue.add(new Integer[]{dx, dy});
                    visited[dx][dy] = true;
                    maze[dx][dy] = maze[parentx][parenty] + 1;
                }
            }
        }
    }
    public static boolean isSafe(int x, int y){
        return x>=1&&x<=N&&y>=1&&y<=M;
    }
}
/*
중간에 미로찾기라는 특성으로 인해 확인해야할 상황이 많아서 귀찮지만,
차근차근 하다보면 충분히 할 수 있다.
 */