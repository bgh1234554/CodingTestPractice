package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2098NoComment {
    static int N;
    static int[][] cost;
    static int[][] dp;
    //dp[city][visited] = 현재까지 방문한 도시의 리스트가 visited 일 때,
    //남은 도시를 모두 방문하는데 드는 최소의 비용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][1<<N]; //비트 연산자 이용. 두번째 인덱스가 리스트이니까.
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                cost[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        //문제에서는 1번 도시부터 주어지나, 계산하기 편하게 0번부터 있는 걸로 계산하기
        System.out.println(tsp2(0,1));
    }
    //top-down 방식의 DP
    private static int tsp2(int city, int visited) {
        //모든 도시 다 방문했다면...
        if(visited==((1<<N)-1)){
            //돌아갈 수 있을 경우 돌아가는 비용을 반환한다.
            return cost[city][0] == 0 ? Integer.MAX_VALUE : cost[city][0];
        }
        if(dp[city][visited]!=0){
            return dp[city][visited]; //메모이제이션 활용
        }
        //아직 데이터가 없는 경우, 만들어야 한다. 최솟값을 찾아야하니 일단 무한대로 초기화
        dp[city][visited]=Integer.MAX_VALUE;
        for(int i=0;i<N;i++){
            //방문한 도시를 확인해서 방문하지 않은 도시가 있고, 그 도시로 갈 수 있는 경우
            //거리 정보를 업데이트한다.
            if((visited & (1<<i))==0 && cost[city][i]!=0){
                //city에서 중간 경유지 i를 거친다. i에서 방문한 도시 리스트가 다음과 같을 때의 최소 비용을 더해서
                //현재 dp의 최솟값과 비교한다.
                dp[city][visited] = Math.min(dp[city][visited],tsp2(i,(visited | 1<<i))+cost[city][i]);
            }
        }
        return dp[city][visited];
    }
}
