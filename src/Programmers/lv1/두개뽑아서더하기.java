package Programmers.lv1;

import java.util.*;

/*
문제 설명

정수 배열 numbers가 주어집니다.
numbers에서 서로 다른 인덱스에 있는 두 개의 수를 뽑아 더해서
만들 수 있는 모든 수를 배열에 오름차순으로 담아 return 하도록 solution 함수를 완성해주세요.

제한사항

    numbers의 길이는 2 이상 100 이하입니다.
    numbers의 모든 수는 0 이상 100 이하입니다.
 */
public class 두개뽑아서더하기 {
    public static void main(String[] args) {

    }
    public int[] solution(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<numbers.length-1; i++){
            for(int j=i+1; j<numbers.length; j++){
                int sum = numbers[i] + numbers[j];
                set.add(sum);
            }
        }
        int[] answer = set.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(answer);

        return answer;
    }

    public int[] solutionWithNoHashSet(int[] numbers) {
        boolean[] possible = new boolean[201]; // 합이 나올 수 있는 범위: 0~200

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                possible[numbers[i] + numbers[j]] = true;
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int sum = 0; sum <= 200; sum++) {
            if (possible[sum]) answer.add(sum);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

/*
이 문제는 n≤100이라는 작은 제한 자체가 "브루트포스가 정답이다"라는 걸 암시하는 문제야.
실무에서도 이런 판단은 중요해 — 입력 크기가 애초에 작다는 걸 알면,
억지로 더 복잡한 알고리즘(정렬+투포인터, 이분탐색 등)을 욱여넣는 게 오히려 코드만 복잡해지고 실익은 없는 경우가 많거든.

"단순무식하게 풀어서 끝나버렸다"는 느낌이 든 건,
사실 이 문제가 정확히 그 정도 난이도로 설계됐기 때문이야
— 네가 못 찾은 게 아니라, 애초에 더 나은 알고리즘이 없는 문제였어.
 */