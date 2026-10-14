package Programmers.lv2;

import java.util.*;

/*
문제 설명

경화는 과수원에서 귤을 수확했습니다.
경화는 수확한 귤 중 'k'개를 골라 상자 하나에 담아 판매하려고 합니다.
그런데 수확한 귤의 크기가 일정하지 않아 보기에 좋지 않다고 생각한 경화는
귤을 크기별로 분류했을 때 서로 다른 종류의 수를 최소화하고 싶습니다.

예를 들어, 경화가 수확한 귤 8개의 크기가 [1, 3, 2, 5, 4, 5, 2, 3] 이라고 합시다.
경화가 귤 6개를 판매하고 싶다면, 크기가 1, 4인 귤을 제외한 여섯 개의 귤을 상자에 담으면,
귤의 크기의 종류가 2, 3, 5로 총 3가지가 되며 이때가 서로 다른 종류가 최소일 때입니다.

경화가 한 상자에 담으려는 귤의 개수 k와 귤의 크기를 담은 배열 tangerine이 매개변수로 주어집니다.
경화가 귤 k개를 고를 때 크기가 서로 다른 종류의 수의 최솟값을 return 하도록 solution 함수를 작성해주세요.

제한사항

    1 ≤ k ≤ tangerine의 길이 ≤ 100,000
    1 ≤ tangerine의 원소 ≤ 10,000,000

 */
public class 귤고르기 {
    public static void main(String[] args) {

    }
    public int solution(int k, int[] tangerine) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int t : tangerine){
            map.put(t,map.getOrDefault(t,0)+1);
        }
        /*
        이 문제의 핵심!
            HashMap 자체는 정렬할 수 없고, entry를 리스트로 꺼내서 정렬하면 돼.
            HashMap은 순서를 보장하지 않으니까 정렬 결과를 담아 둘 곳이 필요해.
         */
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1,o2)->o2.getValue()-o1.getValue());
        int answer = 0;
        int count = 0;
        while(count<k){
            for(Map.Entry<Integer,Integer> entry : list){
                count+=entry.getValue();
                answer++;
                if(count>=k) break;
            }
        }
        return answer;
    }

    //List에 Map.Entry를 굳이 안담아도 된다. 갯수만 필요하니까.
    public int solutionByClaude(int k, int[] tangerine) {
        // 크기별 개수 세기
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int t : tangerine) cnt.merge(t, 1, Integer::sum);

        // 개수만 필요하므로 values를 내림차순 정렬
        List<Integer> counts = new ArrayList<>(cnt.values());
        counts.sort(Collections.reverseOrder());

        // 개수가 많은 종류부터 담다가 k개를 채우면 종료
        int picked = 0;
        int kinds = 0;
        for (int c : counts) {
            picked += c;
            kinds++;
            if (picked >= k) break;
        }
        return kinds;
    }
}
