package DoIt.Chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        //BaekJoon11286에 보면 comparator로 (o1, o2)로 어떻게 비교할지 정할 수 있다.
        //comparable은 자기 자신과 다른 객체를 비교할 때 사용하는 것.
        for(int i=0;i<N;i++){
            pq.add(Integer.parseInt(br.readLine()));
        }
        int sum=0;
//        if(pq.size()==1){
//            sum=pq.poll();
//        }
//        else if(pq.size()==2){
//            sum+=pq.poll();sum+=pq.poll();
//        }
        while(pq.size()>1){
            int first = pq.poll();
            int second = pq.poll();
            pq.add(first+second);
            sum+=first+second;
        }
        System.out.println(sum);
    }
    /*
    데이터의 삽입, 삭제, 정렬이 필요할 때는 - 우선순위 큐를 사용하면 도움된다.
    카드 더미의 수가 1개일때 2개일때는 따로 처리를 해야하나 하고 고민했지만, 생각해보니 필요가 없었다.
     */
}
