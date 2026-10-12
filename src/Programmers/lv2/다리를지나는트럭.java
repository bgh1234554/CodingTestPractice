package Programmers.lv2;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/*
문제 설명

트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다.
모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다.
다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며,
다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다.
무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

경과 시간 	다리를 지난 트럭 	다리를 건너는 트럭 	대기 트럭
0 	[] 	[] 	[7,4,5,6]
1~2 	[] 	[7] 	[4,5,6]
3 	[7] 	[4] 	[5,6]
4 	[7] 	[4,5] 	[6]
5 	[7,4] 	[5] 	[6]
6~7 	[7,4,5] 	[6] 	[]
8 	[7,4,5,6] 	[] 	[]

따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length,
다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다.
이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

제한 조건

    bridge_length는 1 이상 10,000 이하입니다.
    weight는 1 이상 10,000 이하입니다.
    truck_weights의 길이는 1 이상 10,000 이하입니다.
    모든 트럭의 무게는 1 이상 weight 이하입니다.
 */
public class 다리를지나는트럭 {
    public static void main(String[] args) {
        System.out.println(solution(2,10,new int[]{7,4,5,6}));
        System.out.println(solution(100,100,new int[]{10}));
        System.out.println(solution(100,100,new int[]{10,10,10,10,10,10,10,10,10,10}));
    }
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        Queue<Integer> waiting = new LinkedList<>();
        Queue<Integer> crossing = new LinkedList<>();
        Queue<Integer> finished = new LinkedList<>();
        for (int truck_weight : truck_weights) {
            waiting.offer(truck_weight);
        }

        //핵심 아이디어 - 0이라는 dummy를 넣어서 매초 시간이 지나가고 있음을 표현
        // -> 큐 길이가 고정된다.
        for(int i=0;i<bridge_length;i++){
            crossing.offer(0);
        }

        //둘다 비어야 끝나니까
        while(finished.size() != truck_weights.length){
            answer++; //1초 지남

            //crossing에서 finished로 갈 수 있는지 검토
            if(!crossing.isEmpty()){
                if(crossing.peek()!=0){
                    finished.add(crossing.poll());
                }
                else crossing.poll();
            }

            //waiting에서 crossing으로 갈 수 있는지 검토
            if(!waiting.isEmpty() &&
                    weight >= crossing.stream().mapToInt(Integer::intValue).sum() + waiting.peek()
//                    //0인건 dummy니까 빼야하니까
//                    //-> 필요 없는 조건. 꽉찬 queue에 대해 위에서 poll을 하니까 항상 참이 됨.
//                     && crossing.stream().filter(t->t==0).count()<bridge_length
            ){
                crossing.offer(waiting.poll());
            }
            else crossing.offer(0);
        }
        return answer;
    }

    //Queue가 굳이 3개나 필요가 없고, weight 계산도 매번 stream으로 하면 비싸다.
    //finished : 안에 든 트럭을 한 번도 꺼내 쓰지 않고 size()만 읽었으니까 정수 하나로 대체.
    //waiting : 입력이 이미 배열이라서 인덱스 하나로 대체.
    public int solutionByClaude(int bridge_length, int weight, int[] truck_weights) {
        // 다리 = 길이가 bridge_length로 고정된 큐. 빈 칸은 0으로 표현
        Queue<Integer> bridge = new ArrayDeque<>(); //다리만 큐가 필요하고 나머지는 대체
        for (int i = 0; i < bridge_length; i++) bridge.offer(0);

        int time = 0;
        int bridgeWeight = 0; // 다리 위 트럭 무게 합 (매번 stream으로 더하지 않고 들고 다닌다)
        int idx = 0;          // 다음에 올라갈 트럭의 인덱스
                              // -> 큐가 필요 없음. 어차피 배열과 동일하기 때문. 대신에 포인터로 대체.
        int finished = 0;     // 다리를 완전히 지난 트럭 수 -> 큐가 필요 없음. 숫자만 필요하니까.

        while (finished < truck_weights.length) {
            time++; // 1초 경과: 다리가 한 칸 전진

            // 맨 앞 칸이 다리를 벗어남
            int out = bridge.poll();
            bridgeWeight -= out;
            if (out != 0) finished++;

            // 빈 맨 뒤 칸에 다음 트럭이 올라갈 수 있으면 올리고, 아니면 빈 칸(0)
            if (idx < truck_weights.length && bridgeWeight + truck_weights[idx] <= weight) {
                bridge.offer(truck_weights[idx]);
                bridgeWeight += truck_weights[idx++];
            } else {
                bridge.offer(0);
            }
        }
        return time;
    }
}
