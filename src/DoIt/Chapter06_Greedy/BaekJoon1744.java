package DoIt.Chapter06_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BaekJoon1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        //내림차순으로 정리해야 큰 수가 먼저 빠져나온다.
        int cntOne=0, cntZero=0;
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            if(num==1){
                cntOne++;
            }
            else if(num==0){
                cntZero++;
            }
            else if(num>1){
                plus.add(num);
            }
            else if(num<0){
                minus.add(num);
            }
        }
        int sum=0;
        while(plus.size()>1){
            int first = plus.poll();
            int second = plus.poll();
            sum+=first*second;
        }
        if(plus.size()==1){
            sum+=plus.poll();
        }
        while(minus.size()>1){
            int first = minus.poll();
            int second = minus.poll();
            sum+=first*second;
        }
        if(minus.size()==1) {
            if (cntZero > 0) {
                minus.poll();
            } else {
                sum += minus.poll();
            }
        }
        sum+=cntOne;
        System.out.println(sum);
    }
}
/*
그리디에서 PQ를 많이 쓰는 것 같다.
그리디를 효율적으로 쓰기 위해서는 데이터가 정렬되어 있어야 하고,
순간순간 최선의 선택을 했을 때, 그것이 전체의 선택으로 이어지기 가장 쉽게 하도록 하기 위한 방법이어서 그런건가?
 */