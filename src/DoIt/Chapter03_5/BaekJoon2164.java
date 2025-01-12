package DoIt.Chapter03_5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BaekJoon2164 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<=N;i++){
            queue.add(i);
        }
        if(queue.size()!=1){
            queue.poll();
        }
        while(queue.size()>1){
            int first = queue.poll();
            queue.add(first);
            queue.poll();
        }
        /*
        while(queue.size()>1){
            queue.poll();
            int first = queue.poll();
            queue.add(first);
        }
        이렇게 하는게 더 깔끔하다.
         */
        System.out.println(queue.poll());
    }
}
