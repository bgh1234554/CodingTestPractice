package Programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
슈퍼 게임 개발자 오렐리는 큰 고민에 빠졌다.
그녀가 만든 프랜즈 오천성이 대성공을 거뒀지만, 요즘 신규 사용자의 수가 급감한 것이다.
원인은 신규 사용자와 기존 사용자 사이에 스테이지 차이가 너무 큰 것이 문제였다.

이 문제를 어떻게 할까 고민 한 그녀는 동적으로 게임 시간을 늘려서 난이도를 조절하기로 했다.
역시 슈퍼 개발자라 대부분의 로직은 쉽게 구현했지만, 실패율을 구하는 부분에서 위기에 빠지고 말았다.
오렐리를 위해 실패율을 구하는 코드를 완성하라.

실패율은 다음과 같이 정의한다.
    - 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수

전체 스테이지의 개수 N,
게임을 이용하는 사용자가 현재 멈춰있는 스테이지의 번호가 담긴 배열 stages가 매개변수로 주어질 때,
실패율이 높은 스테이지부터 내림차순으로 스테이지의 번호가 담겨있는 배열을 return 하도록 solution 함수를 완성하라.

제한사항

    스테이지의 개수 N은 1 이상 500 이하의 자연수이다.
    stages의 길이는 1 이상 200,000 이하이다.
    stages에는 1 이상 N + 1 이하의 자연수가 담겨있다.
        각 자연수는 사용자가 현재 도전 중인 스테이지의 번호를 나타낸다.
        단, N + 1 은 마지막 스테이지(N 번째 스테이지) 까지 클리어 한 사용자를 나타낸다.
    만약 실패율이 같은 스테이지가 있다면 작은 번호의 스테이지가 먼저 오도록 하면 된다.
    스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.

 */
public class 실패율 {
    public static void main(String[] args) {
        int N1 = 5;
        int[] stages1 = {2, 1, 2, 6, 2, 4, 3, 3};
        System.out.println(Arrays.toString(solution(N1, stages1))); // [3, 4, 2, 1, 5]

        int N2 = 4;
        int[] stages2 = {4, 4, 4, 4, 4};
        System.out.println(Arrays.toString(solution(N2, stages2)));
    }
    public static int[] solution(int N, int[] stages) {
        int[] answer;

        //스테이지 별 실패율 저장
        PriorityQueue<Rate> pq = new PriorityQueue<>();

        //1부터 N+1 인덱스 사용
        int[] failed = new int[N+2]; //i번째 스테이지를 클리어하는데 실패한 사람
        for(int stage:stages){
            failed[stage]++;
        }

        int userSum = 0; //
        for(int i=1;i<=N;i++){
            userSum+=failed[i-1];
            if(stages.length-userSum==0){
                //스테이지에 도달한 유저가 없는 경우 해당 스테이지의 실패율은 0 으로 정의한다.
                //위 조건을 처리하기 위한 조건문
                pq.add(new Rate(i,0));
                continue;
            }
            pq.add(new Rate(i, (double) failed[i] /(stages.length-userSum)));
        }

//       오답 - answer = pq.stream().mapToInt(x->x.stage).toArray();
        //pq를 stream으로 꺼내면 순서 보장이 안됨.

        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty()) {
            result.add(pq.poll().stage);
        }
        answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    static class Rate implements Comparable<Rate>{
        int stage;
        double rate;
        Rate(int stage, double rate){
            this.stage = stage;
            this.rate = rate;
        }

        @Override
        public int compareTo(Rate o) {
            if(this.rate==o.rate){ //동일한 실패율 기록 시 스테이지 순서대로
                return Integer.compare(this.stage,o.stage);
            }
            return Double.compare(o.rate,rate); //분수끼리 비교할때 쓰는 것!
        }
    }

    /*
    [기억해야 할 것들 - 실패율 문제에서 배운 함정 2가지]

    1) double 값끼리 뺀 결과를 (int)로 캐스팅하면 안 된다.
       (int) 캐스팅은 소수점 이하를 그냥 버려버린다(truncate).
       이 문제의 실패율은 0~1 사이 값이라 두 rate의 차이가 항상 1보다 작고,
       그 결과 (int)(o.rate - this.rate)는 거의 항상 0이 되어
       "모든 스테이지의 실패율이 같다"고 잘못 판정하게 된다.
       → 예전 코드(버그): return (int)(o.rate - this.rate);
       → 수정 코드: return Double.compare(o.rate, this.rate);
       double 비교는 무조건 Double.compare(a, b)를 써야 캐스팅 없이 안전하게 -1/0/1을 반환해준다.

    2) PriorityQueue를 stream()이나 iterator로 그냥 순회하면 우선순위 순서가 보장되지 않는다.
       PriorityQueue는 내부적으로 힙(heap) 구조로 데이터를 저장하는데,
       힙은 "가장 우선순위 높은 원소가 루트에 있다"만 보장하지,
       나머지 원소들까지 정렬된 상태로 배열에 들어있는 게 아니다.
       즉 순서가 보장되는 시점은 오직 poll()을 반복 호출할 때뿐이다.
       → 예전 코드(버그): pq.stream().mapToInt(x -> x.stage).toArray();
       → 수정 코드: while(!pq.isEmpty()) result.add(pq.poll().stage); 로 하나씩 꺼내야 함
    */


    /*
    정렬 대상 배열과 정렬 기준이 되는 배열을 분리해서, comparator 안에서 서로 다른 배열끼리 참조하는 패턴
     */
    public static int[] solutionWithoutPQ(int N, int[] stages) {
        // 스테이지별로 "그 스테이지에 도달했지만 아직 클리어하지 못한(멈춰있는)" 인원 수
        // 완주한 사람은 stage 값이 N+1로 들어오므로, 인덱스 N+1까지 담을 수 있게 크기를 N+2로 잡음
        int[] stuckCount = new int[N + 2];
        for (int stage : stages) {
            stuckCount[stage]++;
        }

        // 스테이지별 실패율 저장 (사용하는 인덱스는 1~N)
        double[] failRate = new double[N + 1];

        // remain: 현재 스테이지 i에 "도달한" 인원 수 (i에 멈춘 사람 + i보다 나중 스테이지에 있는 사람 모두 포함)
        // 모든 참가자는 1번 스테이지부터 시작하므로 최초값은 전체 인원 수
        int remain = stages.length;

        for (int i = 1; i <= N; i++) {
            // 실패율 = (해당 스테이지에 멈춘 인원) / (해당 스테이지에 도달한 인원)
            // 도달한 인원이 0명이면 나눗셈이 불가능하므로 0으로 처리
            failRate[i] = (remain == 0) ? 0 : (double) stuckCount[i] / remain;

            // 다음 스테이지(i+1)로 넘어가기 전에, 이번 스테이지에 멈춘 인원만큼 remain에서 제외
            // → 다음 반복의 remain은 "스테이지 i+1에 도달한 인원 수"가 됨
            remain -= stuckCount[i];
        }

        // 정렬 대상은 스테이지 번호(1~N) 자체가 아니라 failRate 배열의 "인덱스"
        // 배열을 직접 정렬하면 실패율 값끼리만 섞이고 어떤 스테이지였는지 알 수 없으므로,
        // 스테이지 번호를 담은 별도 배열을 만들어 이 배열을 정렬한다
        Integer[] stageOrder = new Integer[N];
        for (int i = 0; i < N; i++) stageOrder[i] = i + 1;

        // stageOrder 안의 스테이지 번호(a, b)를 각각 failRate 배열의 인덱스로 사용해
        // 실제 실패율 값을 비교 → 실패율 내림차순 정렬
        // Arrays.sort(Object[])는 안정 정렬(stable sort)이므로, 실패율이 같으면
        // stageOrder에 애초에 오름차순으로 넣어둔 원래 순서(스테이지 번호 순)가 그대로 유지됨
        Arrays.sort(stageOrder, (a, b) -> Double.compare(failRate[b], failRate[a]));

        // Integer[] → int[] 변환해서 반환
        return Arrays.stream(stageOrder).mapToInt(Integer::intValue).toArray();
    }
}