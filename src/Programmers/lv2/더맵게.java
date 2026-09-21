package Programmers.lv2;

import java.util.PriorityQueue;

/*
문제 설명

매운 것을 좋아하는 Leo는 모든 음식의 스코빌 지수를 K 이상으로 만들고 싶습니다.
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해,
Leo는 스코빌 지수가 가장 낮은 두 개의 음식을 아래와 같이 특별한 방법으로 섞어 새로운 음식을 만듭니다.

섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때,
모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.

제한 사항

    scoville의 길이는 2 이상 1,000,000 이하입니다.
    K는 0 이상 1,000,000,000 이하입니다.
    scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
    모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
 */
public class 더맵게 {
    public static void main(String[] args) {

    }

    //PQ는 offer poll peek
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for(int scov : scoville){
            pq.offer(scov);
        }
        while(pq.size()>=2){
            int first = pq.poll();
            if(first>=K) return answer;
            int second = pq.poll();
            int mixed = first + 2*second;
            pq.offer(mixed);
            answer++;
        }
        return pq.peek()>=K? answer:-1;
    }

    //마지막에 다 섞고 나서 스코빌이 K를 넘는지 검사하지 않았다.
    public int solutionFailed(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;
        for(int scov : scoville){
            pq.offer(scov);
        }
        while(pq.size()>=2){
            int first = pq.poll();
            if(first>=K) return answer;
            int second = pq.poll();
            int mixed = first + 2*second;
            pq.offer(mixed);
            answer++;
        }
        return -1;
    }
}
