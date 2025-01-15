package DoIt.Chapter04.Chapter04_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon1377 {
    public static void main(String[] args) throws IOException {
        //N값과 배열에 들어가는 값이 크기 때문에, Scanner 사용시 메모리 초과 발생.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node[] arr = new Node[N];
        for(int i=0;i<N;i++){
            arr[i]=new Node(i,Integer.parseInt(br.readLine()));
        }
        Arrays.sort(arr);
        int max = 0;
        //정렬한 배열과 원래의 배열, 두 배열을 놔두고 일일이 비교하면, 결국에 시간 복잡도가 O(N^2)이 되는 것은 같다.
        //배열을 하나로 만들고, 처음 배열을 저장할 때, 처음의 인덱스를 가지게 만드는 것이 좋은 것 같다.
        for(int i=0;i<N;i++) {
            if (max < arr[i].index - i) {
                max = arr[i].index - i;
            }
        }
        //change==false가 되게 확정짓는 정렬이 다 된 loop를 한번 더 돌기 때문에 +1 해야 한다.
        System.out.println(max+1);
    }

    //Comparable vs Comparator
    //Comparable은 클래스 자신과 다른 클래스를 인수로 받는 compareTo 메서드를 구현하지만,
    //Comparator은 클래스 자신과 상관없이, 두 객체를 비교하는 compare 메서드를 구현한다.
    //
    static class Node implements Comparable<Node> {
        public int index;
        public int val;

        public Node(int index, int val) {
            super();
            this.index = index;
            this.val=val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val-o.val;
        }
    }
}
