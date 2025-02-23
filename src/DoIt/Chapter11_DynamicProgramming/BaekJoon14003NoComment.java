package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon14003NoComment {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];
        int[] check = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        int maxLength = 0;
        for(int i=1;i<=N;i++){
            //삽입해야 하는 위치 찾기. Arrays.binarySearch 메서드의 경우 시작점은 포함, 끝점은 미포함이다.
            //List에 사용하는 Collections.binarySearch의 경우에는 시작점 끝점이 필요가 없어서 구현하기 더 편하다.
            //그래서 Copilot이 추천해준 것 같다.
            int insert = Arrays.binarySearch(check,1,maxLength+1,arr[i]);
            //만약 중복된 값이 들어오지 않을 경우, binarySeach는 -(삽입위치+1)을 리턴하기 때문에,
            //삽입위치로 바꿔주기 위해서 다음과 같은 연산을 수행한다.
            if(insert<0) insert = -(insert+1);
            //check배열에는 특정 길이(=인덱스)의 증가하는 부분 수열의 마지막 값을 저장
            //현재 원소보다 작은 값을 지니고 있는 최장 증가 수열의 길이를 찾을 때 사용한다.
//            check[insert] = Math.min(check[insert],arr[i]);
            check[insert]=arr[i];
            dp[i]=insert;
            maxLength = Math.max(maxLength,insert); //최대 수열 길이 업데이트
        }
        StringBuilder sb = new StringBuilder();
        sb.append(maxLength).append("\n");
        int[] result = new int[maxLength];
        //sb.insert는 시간이 오래 걸린다. append로 할거면 result 배열에 다시 담아서 구현해야 한다.
        //거꾸로 탐색하면서 감소하는 수열 담기
        for(int i=N;i>=1;i--){
            if(dp[i]==maxLength){
                //뒤에서부터 담아야 하니까
                result[--maxLength]=arr[i];
            }
        }
        for(int num: result){
            sb.append(num).append(" ");
        }
        System.out.println(sb);
    }
    /*
    insert로 구현하는 방식
    StringBuilder sb = new StringBuilder();
        sb.append(maxLength).append("\n");
        int x = check[maxLength] + 1;
        for (int i = N; i >= 1; i--) {
            if (dp[i] == maxLength && arr[i] < x) {
                sb.insert(0, arr[i] + " ");
                x = arr[i];
                maxLength--;
            }
        }
     */
}
