package Programmers.lv2;

import java.util.LinkedList;
import java.util.Queue;

/*
 문제 설명

ROR 게임은 두 팀으로 나누어서 진행하며, 상대 팀 진영을 먼저 파괴하면 이기는 게임입니다.
따라서, 각 팀은 상대 팀 진영에 최대한 빨리 도착하는 것이 유리합니다.

지금부터 당신은 한 팀의 팀원이 되어 게임을 진행하려고 합니다.
다음은 5 x 5 크기의 맵에, 당신의 캐릭터가 (행: 1, 열: 1) 위치에 있고, 상대 팀 진영은 (행: 5, 열: 5) 위치에 있는 경우의 예시입니다.

https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/dc3a1b49-13d3-4047-b6f8-6cc40b2702a7/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B51_sxuruo.png

위 그림에서 검은색 부분은 벽으로 막혀있어 갈 수 없는 길이며, 흰색 부분은 갈 수 있는 길입니다.
캐릭터가 움직일 때는 동, 서, 남, 북 방향으로 한 칸씩 이동하며, 게임 맵을 벗어난 길은 갈 수 없습니다.

아래 예시는 캐릭터가 상대 팀 진영으로 가는 두 가지 방법을 나타내고 있습니다.

첫 번째 방법은 11개의 칸을 지나서 상대 팀 진영에 도착했습니다.

https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/9d909e5a-ca95-4088-9df9-d84cb804b2b0/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B52_hnjd3b.png

두 번째 방법은 15개의 칸을 지나서 상대팀 진영에 도착했습니다.

https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/4b7cd629-a3c2-4e02-b748-a707211131de/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B53_ntxygd.png

위 예시에서는 첫 번째 방법보다 더 빠르게 상대팀 진영에 도착하는 방법은 없으므로, 이 방법이 상대 팀 진영으로 가는 가장 빠른 방법입니다.

만약, 상대 팀이 자신의 팀 진영 주위에 벽을 세워두었다면 상대 팀 진영에 도착하지 못할 수도 있습니다.
예를 들어, 다음과 같은 경우에 당신의 캐릭터는 상대 팀 진영에 도착할 수 없습니다.

https://grepp-programmers.s3.ap-northeast-2.amazonaws.com/files/production/d963b4bd-12e5-45da-9ca7-549e453d58a9/%E1%84%8E%E1%85%AC%E1%84%83%E1%85%A1%E1%86%AB%E1%84%80%E1%85%A5%E1%84%85%E1%85%B54_of9xfg.png

게임 맵의 상태 maps가 매개변수로 주어질 때,
캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성해주세요.
단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 해주세요.
 */
public class 게임맵최단거리 {
    public static void main(String[] args) {

    }
    static int m,n;
    //가로 세로 n*m이 너무 헷갈린다...
    public static int solution(int[][] maps) {
        //BFS를 위한 초기 설정 세팅 (우측 하단이 n,m이니까)
        //(헷갈리면 좌표 하나씩 써보면서 감 익히기)
        n = maps.length;
        m = maps[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        int[][] directions = {{0,1},{0,-1},{1,0},{-1,0}};
        int answer = n*m;

        //BFS 시작
        //거리가 아니라 칸 수니까 1로 시작.
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] pos = queue.poll();
            if(pos[0] == n-1 && pos[1] == m-1){
                visited[pos[0]][pos[1]] = true;
                answer = Math.min(answer,pos[2]);
                continue;
            }
            for(int[] direction:directions){
                int x = pos[0] + direction[0];
                int y = pos[1] + direction[1];
                //항상 isSafe를 제일 먼저. 안그러면 outOfBounds 오류가 난다.
                if(isSafe(x,y)&&!visited[x][y]&&maps[x][y]==1){
                    visited[x][y]=true;
                    queue.offer(new int[]{x,y,pos[2]+1});
                }
            }
        }
        return visited[n-1][m-1] ? answer : -1;
    }
    /*
    n x m 표기는 관습(n=행, m=열)일 뿐 문제마다 검증 필요 — 좌표 예시나 "좌측 상단/우측 하단" 문장으로 확인
    조건문에서 경계 체크(isSafe)는 항상 배열 접근보다 먼저 와야 함 (short-circuit 활용)
     */

    public static boolean isSafe(int x, int y){
        return x>=0&&y>=0&&x<n&&y<m;
    }
}
