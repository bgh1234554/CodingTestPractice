package Programmers.lv1;

import java.util.*;

/*
문제 설명

당신은 폰켓몬을 잡기 위한 오랜 여행 끝에, 홍 박사님의 연구실에 도착했습니다.
박사님은 당신에게 자신의 연구실에 있는 총 N 마리의 폰켓몬 중에서 N/2마리를 가져가도 좋다고 했습니다.
홍 박사님 연구실의 폰켓몬은 종류에 따라 번호를 붙여 구분합니다.
따라서 같은 종류의 폰켓몬은 같은 번호를 가지고 있습니다.
예를 들어 연구실에 총 4마리의 폰켓몬이 있고, 각 폰켓몬의 종류 번호가 [3번, 1번, 2번, 3번]이라면
이는 3번 폰켓몬 두 마리, 1번 폰켓몬 한 마리, 2번 폰켓몬 한 마리가 있음을 나타냅니다.
이때, 4마리의 폰켓몬 중 2마리를 고르는 방법은 다음과 같이 6가지가 있습니다.

    첫 번째(3번), 두 번째(1번) 폰켓몬을 선택
    첫 번째(3번), 세 번째(2번) 폰켓몬을 선택
    첫 번째(3번), 네 번째(3번) 폰켓몬을 선택
    두 번째(1번), 세 번째(2번) 폰켓몬을 선택
    두 번째(1번), 네 번째(3번) 폰켓몬을 선택
    세 번째(2번), 네 번째(3번) 폰켓몬을 선택

이때, 첫 번째(3번) 폰켓몬과 네 번째(3번) 폰켓몬을 선택하는 방법은 한 종류(3번 폰켓몬 두 마리)의 폰켓몬만 가질 수 있지만,
다른 방법들은 모두 두 종류의 폰켓몬을 가질 수 있습니다.
따라서 위 예시에서 가질 수 있는 폰켓몬 종류 수의 최댓값은 2가 됩니다.

당신은 최대한 다양한 종류의 폰켓몬을 가지길 원하기 때문에,
최대한 많은 종류의 폰켓몬을 포함해서 N/2마리를 선택하려 합니다.

N마리 폰켓몬의 종류 번호가 담긴 배열 nums가 매개변수로 주어질 때,
N/2마리의 폰켓몬을 선택하는 방법 중, 가장 많은 종류의 폰켓몬을 선택하는 방법을 찾아,
그때의 폰켓몬 종류 번호의 개수를 return 하도록 solution 함수를 완성해주세요.

제한사항

    nums는 폰켓몬의 종류 번호가 담긴 1차원 배열입니다.
    nums의 길이(N)는 1 이상 10,000 이하의 자연수이며, 항상 짝수로 주어집니다.
    폰켓몬의 종류 번호는 1 이상 200,000 이하의 자연수로 나타냅니다.
    가장 많은 종류의 폰켓몬을 선택하는 방법이 여러 가지인 경우에도,
    선택할 수 있는 폰켓몬 종류 개수의 최댓값 하나만 return 하면 됩니다.
*/
public class 폰켓몬 {
    public static void main(String[] args) {

    }
    /*
    어차피 종류의 최댓값만 return하면 되고, 최대 pick 수도 정해져 있으니까,
    최대한 골고루 골라야 하는게 핵심이니까, 처음부터 쭉 고른다.
    -> 처음부터 종류별로 쭉 세서, 몇종류인지 센 다음에 그 종류만큼 골고루 고르면 된다.
    다만, N/2라는 상한선이 있기 때문에 마지막에 Math.min으로 최댓값은 nums.length/2가 되게 한다.
     */
    public static int solution(int[] nums) {
        int[] count = new int[200002];
        int types = 0;
        for(int num:nums){
            if(count[num] == 0){
                types++;
            }
            count[num]++;
        }
        return Math.min(types,nums.length/2);
    }

    /*
    위의 풀이는 문제 제한사항에서 폰켓몬의 종류 번호를 20만 이하로 제한시켰기 때문에 풀 수 있는 것이다.
    실무 데이터에서는 이러한 보장의 의미가 없는 값 (UUID 등)이 들어올 수 있기 때문에 이 땐, 해시맵으로 푸는 것이 좋다.
     */

    // HashSet 버전 — "종류가 몇 개인지"만 필요하므로 가장 적합한 자료구조
    public static int solutionHashSet(int[] nums) {
        Set<Integer> types = new HashSet<>();
        for (int num : nums) {
            types.add(num); // 이미 있는 값이면 자동으로 무시됨 (중복 저장 안 됨)
        }
        return Math.min(types.size(), nums.length / 2);
    }

    // HashMap 버전 — 종류별 "개수"까지 필요한 경우를 대비한 확장형
    public static int solutionHashMap(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.merge(num, 1, Integer::sum);
        }
        return Math.min(countMap.size(), nums.length / 2);
    }
    /*
    Map.merge(key, value, remappingFunction) 설명

    - key가 처음 등장 → value를 그대로 저장 (여기선 1)
    - key가 이미 존재 → remappingFunction(기존값, value)의 결과로 값을 덮어씀

    즉 countMap.merge(num, 1, Integer::sum)은 아래 코드와 완전히 동일하게 동작한다:
        countMap.put(num, countMap.getOrDefault(num, 0) + 1);

    getOrDefault 방식과 기능은 100% 같고, 카운팅 로직을 한 줄로 줄여주는
    Java 표준 관용구(idiom)일 뿐이다.
    */
}
