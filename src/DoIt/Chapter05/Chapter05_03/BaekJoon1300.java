package DoIt.Chapter05.Chapter05_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int start = 1; int end = K;
        int ans=0;
        while(start<=end){
            int mid = (start+end)/2;
            int cnt = 0;
            //각 행별로 mid보다 작거나 같은 숫자 세기.
            for(int i=1;i<=N;i++){
                cnt+=Math.min(N,mid/i);
            }
            if(cnt<K){
                start=mid+1;
            }
            else{
                ans=mid;
                end=mid-1;
                //같은 값을 가진 수가 여러개일 수 있기 때문에, cnt>=K인 상황이 되면,
                //정답 변수에 저장하고 다시 탐색해야한다.
                //만약 7*7 배열에서 k값이 6이라면 4를 리턴해야 하는데, mid가 4일때 cnt값은 8이다.
                //그렇다고 이 값을 저장하지 않고 end값만 바꾸면 1에서 3까지만 탐색해서 mid값이 2가 된다.
                //이러면 cnt값이 3이되기 때문에 값을 제대로 찾을 수 없다.
            }
        }
        System.out.println(ans);
    }
}
