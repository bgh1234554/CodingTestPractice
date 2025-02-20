package DoIt.Chapter06_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());
        int[] money = new int[N];
        for(int i=0;i<N;i++){
            money[i]=Integer.parseInt(br.readLine());
        }
        int sum=0, count=0;
        for(int i=N-1;i>=0;i--){
            //풀이 1 - while문으로 알아서 금액 채우기
//            while(sum+money[i]<=goal){
//                sum+=money[i];
//                count++;
//            }
            //반복문 2중으로 돌아서 시간복잡도에 문제가 생길 수 있으니, 한번에 계산하는 방법
            if(money[i]<=goal){
                count+=(goal/money[i]); //몇장 추가할 지
                goal = goal%money[i]; //(돈 단위*장수)만큼 지불하니까, 나머지가 새로운 금액이 된다.
            }
        }
        System.out.println(count);
    }
}
