package DoIt.Chapter03_5;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BaekJoon11286 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)->{
            int first = Math.abs(o1);
            int second = Math.abs(o2);
            if(first==second){
                return o1-o2;
            }
            else{
                return first-second;
            }
            //compare return 값이 양수면 첫번째가 더 큰 것으로 판단하고 서로 자리를 바꾸고,
            //음수면 첫번째가 더 작은 것으로 판단한다.
        });
        for(int i=0;i<N;i++){
            int x = scanner.nextInt();
            if(x==0){
                if(pq.isEmpty()){
                    System.out.println("0");
                }
                else{
                    System.out.println(pq.poll());
                }
            }
            else{
                pq.add(x);
            }
        }
    }
}
/*
PriorityQueue는 우선순위가 높은 값을 먼저 출력하는 형태로,
우선순위를 정의하기 위해서 Comparator 클래스의 compare 메서드를 구현해야 한다.

compare 메서드의 리턴 값이 양수이면, 인자로 받은 o1과 o2중 o1이 더 큰것으로 간주하고 서로의 위치를 바꾸며,
음수면 o2가 더 큰 것으로 간주하고 위치를 바꾸지 않는다.

절댓값이 작으면 작은 수부터 출력하고 (오름차순), 절댓값이 같으면 작은 수부터 (오름차순) 출력해야 한다.
그러면 앞의 수가 더 크면 바꿔야 하니까 o1-o2로 적어야 값이 바뀐다.
 */
