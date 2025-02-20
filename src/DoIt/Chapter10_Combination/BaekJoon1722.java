package DoIt.Chapter10_Combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1722 {
    public static void main(String[] args) throws IOException {
        long[] fact = new long[21];
        fact[0]=1; fact[1]=1;
        int[] arr = new int[21];
        //해당 숫자가 순열에 사용되었는지 유무
        boolean[] visited = new boolean[21];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=2;i<=N;i++){
            fact[i]=fact[i-1]*i;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Q = Integer.parseInt(st.nextToken());
        if(Q==1){
            long K = Long.parseLong(st.nextToken());
            //자리별로 탐색 (앞자리부터)
            for(int i=1;i<=N;i++){
                //숫자별로 탐색
                int cnt=1;
                for(int j=1;j<=N;j++){
                    if(!visited[j]){
                        if(K<=fact[N-i]*cnt){
                            arr[i]=j;
                            visited[j]=true;
                            K-=fact[N-i]*(cnt-1); //하한선 만큼 제거
                            //3!+1번째부터 3!*2번째까지 사이라면, 3!(=(4-1)!*(2-1))만큼은 없애도 되니까.
                            break;
                        }
                        else{
                            cnt++;
                        }
                    }
                }
            }
            for(int i=1;i<=N;i++){
                System.out.print(arr[i]+" ");
            }
        }
        else{
            long K = 1;
            //자리수 별로 탐색
            for(int i=1;i<=N;i++){
                arr[i]=Integer.parseInt(st.nextToken());
                //여기서부터 어렵다
                long cnt=0;
                //들어갈 수 있는 숫자 작은 순으로 탐색.
                for(int j=1;j<arr[i];j++){
                    if(!visited[j]) cnt++;
                }
                K+=cnt*fact[N-i];
                visited[arr[i]]=true;
            }
            System.out.println(K);
        }
    }
}
/*
이전의 조합 문제들과 달리 순열에 대해 나온다.
순열의 순서를 출력하는 문제와, 순서를 주면 순열을 출력하는 두 개의 문제로 나뉘어 있는데,
솔직히 로직이 어려워서 이해하기 힘들다.

핵심 논리
1,2,3,4를 가지고 순열을 만든다고 할 때
1### -> 1번째부터 3!번째까지
2### -> 3!+1번째부터 3!*2번째까지
...

그래서 (n-i)!이 필요하고, 곱하기 몇인지 상한선을 정해주는 cnt가 필요하다. 이후 최소 몇번째인지 알았기 때문에
순서를 나타내는 변수 K에서 빼주거나 더하면 된다. 더하거나 뺄때는 하한선을 더하고 뺀다.
n-i는, n자리수 일때 i번째 자리 앞에 있는 수의 개수는 n-i이기 때문이다.
 */