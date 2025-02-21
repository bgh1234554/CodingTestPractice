package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2098 {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static final int INF = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][(1 << N)];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(tsp(0, 1));
    }
    static int tsp(int current, int visited) {
        if (visited == (1 << N) - 1) {
            //모두 방문했으면 돌아가면 된다. 갈 길이 없을 경우에는 INF를 출력한다고 구현했지만,
            //문제에서는 돌아갈 수 있는 것이 전제되어있어 실행되지 않는다.
            return cost[current][0] == 0 ? INF : cost[current][0];
        }
        if (dp[current][visited] != 0) {
            return dp[current][visited]; //메모이제이션 활용
        }
        //방문한 적이 없는 경우, 최소 경로를 찾는다.
        dp[current][visited] = INF;
        //i를 중간 경로라고 생각하면 된다.
        for (int i = 0; i < N; i++) {
            //i를 방문한 적이 없고 (비트 and 연산자로 확인), 현재 도시에서 i로 가는 경로가 존재하는 경우
            if ((visited & (1 << i)) == 0 && cost[current][i] != 0) {
                dp[current][visited] = Math.min(dp[current][visited],
                        //visited에 i를 방문했다는 정보를 저장하기 위해 shift와 or 연산자 사용.
                        tsp(i, visited | (1 << i))+ cost[current][i]);
            }
        }
        return dp[current][visited];
    }
}
/*
비트 연산자를 활용하는 점화식 DP 문제라니...

컴퓨터 과학에서 중요하게 다뤄지는 문제인 외판원 순회 문제(TSP)의 가장 일반적인 형태이다.
여기서는 모든 경로를 완전탐색하는 방향으로 풀 수 있다. 모든 상황을 가정해야 하기 때문에,
visited 배열을 boolean 배열이 아니라, 현재 도시 위치, 방문한 도시의 상태를 각각 저장하기 위해
비트 연산을 사용한 것이 특징이다.
백준 문제에서는 도시의 번호를 1번부터 매기지만, 0번부터 도시의 번호를 매긴다고 가정하고 푸는 TSP 문제들이 많다.

비트 연산의 사용

1. 방문 여부 확인: 특정 도시가 방문되었는지 확인하기 위해 AND 연산을 사용합니다.
if ((visited & (1 << i)) == 0) {
    // i번 도시는 방문하지 않음
}
여기서 1 << i는 i번째 비트를 1로 설정하는 비트마스크를 생성합니다.

2. 방문 상태 업데이트: 특정 도시를 방문한 상태로 업데이트하기 위해 OR 연산을 사용합니다.
visited | (1 << i)
여기서 visited | (1 << i)는 i번째 도시를 방문한 상태로 업데이트합니다.

3. 비트마스크를 이용한 방문 확인
1 << N은 1을 N번 왼쪽으로 시프트한 값으로, 이는 2의 N승을 의미합니다.
1 << N - 1은 N개의 비트가 모두 1로 설정된 값을 의미합니다.
예를 들어, N이 4라면 1 << 4는 16(2^4)이고, 1 << 4 - 1은 15(1111 in binary)입니다.

ex) 모든 도시 방문 확인
visited 변수는 현재까지 방문한 도시들을 비트마스크로 나타냅니다.
visited == (1 << N) - 1은 모든 도시가 방문되었는지 확인하는 조건입니다.
모든 비트가 1로 설정되어 있으면 모든 도시를 방문한 것입니다.
 */
