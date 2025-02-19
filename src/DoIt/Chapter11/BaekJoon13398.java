package DoIt.Chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] data = new int[N];
        for(int i=0;i<N;i++){
            data[i]=Integer.parseInt(st.nextToken());
        }
        int[] left = new int[N];
        int[] right = new int[N];
        left[0]=data[0]; right[N-1]=data[N-1];
        int result = left[0];
        for(int i=1;i<N;i++){
            //i번째 수를 시작점으로 새롭게 시작하냐
            //기존 최대 합에 i번째 수를 더하느냐
            left[i] = Math.max(left[i-1]+data[i],data[i]);
            result = Math.max(result,left[i]);
        }
        for(int i=N-2;i>=0;i--){
            //여기도 left와 마찬가지 논리. 방향만 반대
            right[i] = Math.max(right[i+1]+data[i],data[i]);
            result = Math.max(result,right[i]);
        }
        //i번째 수를 제거하고 합 계산하기
        for(int i=1;i<N-1;i++){
            //i=0일때는 그냥 R[i+1] 이고, i=N-1일때는 그냥 L[i-1]이니까
            //포함하지 않아도 된다.
            int tmp = left[i-1]+right[i+1];
            result = Math.max(result,tmp);
        }
        System.out.println(result);
    }
}
/*
DP 중에서 배열을 여러개 만들어서 연산을 수행해야 하는 문제는 처음 풀어보았다.
점화식 정의도 못하겠어서 풀이를 보고 유형을 익혔다.

풀이를 곱씹어보고 다시 생각해보면,
어쨌든 구간합의 최대를 구해야 하는거니까,
각 구간의 최댓값을 구하는 걸 DP의 점화식 소재로 삼아야 한다.
0번째부터 i번째까지, "i번째 인덱스를 포함해서" 최댓값을 L[i]
반대로 오른쪽부터 똑같이 한 것을 R[i]라고 하자. 그러면, 특정 자릿수를 포함했을 때의 최댓값은 구할 수 있다.

이후, 특정 인덱스를 무조건 제거했을때의 합을 구할 때는, 그 자리가 없는 셈 쳐야하니까
L[i-1]+R[i+1]을 수행하면 된다.

마지막으로 for문을 돌면서, 지금까지 만든 세가지 경우의 수 중 최댓값을 출력하면 된다.

단순히 0번째부터 i번째 수까지의 최댓값을 D[i]라는 식으로 점화식을 놓고 손으로 풀어보면,
값이 제대로 나오지 않는다.

"If induction process doesn't work, make it stronger"라는 MIT OCW 이산수학 시간에 들은 문구가 떠오른다.
여기서도 처음 생각한 점화식이 제대로 작동하지 않으니, 조건을 더 붙여 점화식을 완성했다.
 */