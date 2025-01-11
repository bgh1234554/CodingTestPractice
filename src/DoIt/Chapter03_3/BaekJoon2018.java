package DoIt.Chapter03_3;

import java.util.Scanner;

public class BaekJoon2018 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int sum = 1, cnt = 1;
        int start = 1, end = 1;
        while(end!=N){
            if(sum==N){cnt++; end++; sum+=end;}
            else if(sum>N) {sum-=start; start++;}
            else if(sum<N) {end++; sum+=end;}
        }
        System.out.println(cnt);
    }
}
/*
투 포인터의 가장 기본적인 예제
a부터 b까지의 연속된 합을 sum이라고 하자. a<=b라고 할 때,
a=b=1로 해놓고, b를 한칸씩 늘려가면서 sum값을 변경한다.
그러다가 sum과 N이 같아지만 cnt++;
N보다 커지면 a 값을 올려서 (b를 더 늘린다고 해서 작아질 수가 없으니까)
다시 sum보다 N이 작아지면 end 값을 늘리면서 확인한다.
 */
