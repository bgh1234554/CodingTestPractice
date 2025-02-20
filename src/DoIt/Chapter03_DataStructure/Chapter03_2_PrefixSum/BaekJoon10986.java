package DoIt.Chapter03_DataStructure.Chapter03_2_PrefixSum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon10986 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long[] sum = new long[N+1]; //편의를 위해 index 1부터 사용한다.
        long[] remainder = new long[M]; //나머지는 0부터 M-1까지 있으니까.
        st = new StringTokenizer(br.readLine()); int data;
        long count=0;
        for(int i=1;i<=N;i++){
            data = Integer.parseInt(st.nextToken());
            sum[i]=sum[i-1]+data;
        }
        for(int i=1;i<=N;i++){
            sum[i]=sum[i]%M; //나머지 연산 수행후
            remainder[(int)sum[i]]++; //값에 해당하는 인덱스의 개수를 올린다.
            if(sum[i]==0)count++; //0이면 경우의 수에 추가.
        }
        for(int i=0;i<M;i++){
            count+=remainder[i]*(remainder[i]-1)/2; //나머지 개수 배열의 값을 보고 순서쌍 2개 선택하는 경우의 수 추가.
        }
        System.out.println(count);
    }
    /*
    이 문제를 풀기 위해서는 다음과 같은 사실을 먼저 알아야만 풀 수 있다.
    * (A+B)%C = (A%C+B%C)%C
    -> 따라서 특정 구간의 부분합의 나머지는 나머지들의 부분합을 구해 거기에 나머지 연산을 취한 것과 같다.

    1. 따라서 일반적인 부분합 배열을 만들었을 때인 A1~Aj까지의 부분합이 M으로 나누어 떨어지는지 확인하기 위해
    부분합 배열에 %M 연산을 취한다.
    2. 이후 0이 나왔을 때는 A1부터 Aj까지의 합을 M으로 나눈 나머지가 0이므로 총 경우의 수에 더한다.
    3. 이제 맨 앞이 1이 아닌 경우를 살펴 보자.
    Ai부터 Aj까지의 부분합은 합 배열에서 S[j]-S[i-1]이다. 부분합의 나머지는 나머지의 부분합을 통해 계산할 수 있다.
    %M연산을 취한 뒤 나머지 끼리 더하는 것이기 때문에 이 연산의 값은 M 미만이므로,
    나누어떨어지려면 S[j]-S[i-1]의 값이 0이어야 한다. 따라서 S[j]=S[i-1]. 값이 같은 두 순서쌍을 찾아야 한다.
    4. 부분합 배열에서 값이 같은 인덱스들을 찾은 뒤, 거기서 2개를 고르는 경우의 수를 총 경우의 수에 더해야 한다.
    5. 그러려면 나머지 별로 인덱스가 몇개인지 알아야 하기 때문에, 미리 나머지를 인덱스로 하며 개수를 세는 배열을 만든다.
    6. 부분합 배열에 나머지 연산을 수행할 때, 값을 확인한 뒤, 값에 해당하는 인덱스에다 개수를 저장한다.
    7. 그러면, A1부터 Aj까지의 부분합의 나머지가 0인 개수를 센 뒤, 나머지별 개수 배열의 값을 확인해,
    거기서 2개를 고르는 경우의 수만큼 총 경우의 수에 더한다. 공식은 nC2. n(n-1)/2.

    의문 -> 가장 큰 값이 10^9인데, 충분히 2^31로 커버 가능하지 않나? 단순히 10자리수라는 의미인건가?
            배열 자료형을 int로 하니 에러가 나고, long으로 하니 통과되었다.
    해답 -> remainder[i]*(remainder[i]-1)/2 연산 수행시,
            remainder배열에 들어갈 수 있는 최댓값은 배열의 크기인데, 여기서는 10^6이다.
            10^6을 2번 곱하면 10^12이므로 int의 범위인 2^31(약 10^9)을 넘어서기 때문에 count는 0이어야 한다.
    출처 - https://www.acmicpc.net/board/view/125802
     */
}
