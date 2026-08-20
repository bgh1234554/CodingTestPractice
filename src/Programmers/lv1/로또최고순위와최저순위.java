package Programmers.lv1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
로또 6/45(이하 '로또'로 표기)는 1부터 45까지의 숫자 중 6개를 찍어서 맞히는 대표적인 복권입니다.
아래는 로또의 순위를 정하는 방식입니다.

순위 	당첨 내용
1 	6개 번호가 모두 일치
2 	5개 번호가 일치
3 	4개 번호가 일치
4 	3개 번호가 일치
5 	2개 번호가 일치
6(낙첨) 	그 외

로또를 구매한 민우는 당첨 번호 발표일을 학수고대하고 있었습니다.
하지만, 민우의 동생이 로또에 낙서를 하여, 일부 번호를 알아볼 수 없게 되었습니다.
당첨 번호 발표 후, 민우는 자신이 구매했던 로또로 당첨이 가능했던 최고 순위와 최저 순위를 알아보고 싶어 졌습니다.

알아볼 수 없는 번호를 0으로 표기하기로 하고,
민우가 구매한 로또 번호 6개가 44, 1, 0, 0, 31 25라고 가정해보겠습니다.
당첨 번호 6개가 31, 10, 45, 1, 6, 19라면,
당첨 가능한 최고 순위와 최저 순위의 한 예는 아래와 같습니다.

당첨 번호 	31 	10 	45 	1 	6 	19 	결과
최고 순위 번호 	31 	0→10 	44 	1 	0→6 	25 	4개 번호 일치, 3등
최저 순위 번호 	31 	0→11 	44 	1 	0→7 	25 	2개 번호 일치, 5등

순서와 상관없이, 구매한 로또에 당첨 번호와 일치하는 번호가 있으면 맞힌 걸로 인정됩니다.
알아볼 수 없는 두 개의 번호를 각각 10, 6이라고 가정하면 3등에 당첨될 수 있습니다.
3등을 만드는 다른 방법들도 존재합니다. 하지만, 2등 이상으로 만드는 것은 불가능합니다.
알아볼 수 없는 두 개의 번호를 각각 11, 7이라고 가정하면 5등에 당첨될 수 있습니다.
5등을 만드는 다른 방법들도 존재합니다. 하지만, 6등(낙첨)으로 만드는 것은 불가능합니다.

민우가 구매한 로또 번호를 담은 배열 lottos, 당첨 번호를 담은 배열 win_nums가 매개변수로 주어집니다.
이때, 당첨 가능한 최고 순위와 최저 순위를 차례대로 배열에 담아서 return 하도록 solution 함수를 완성해주세요.

제한사항

    lottos는 길이 6인 정수 배열입니다.
    lottos의 모든 원소는 0 이상 45 이하인 정수입니다.
        0은 알아볼 수 없는 숫자를 의미합니다.
        0을 제외한 다른 숫자들은 lottos에 2개 이상 담겨있지 않습니다.
        lottos의 원소들은 정렬되어 있지 않을 수도 있습니다.
    win_nums은 길이 6인 정수 배열입니다.
    win_nums의 모든 원소는 1 이상 45 이하인 정수입니다.
        win_nums에는 같은 숫자가 2개 이상 담겨있지 않습니다.
        win_nums의 원소들은 정렬되어 있지 않을 수도 있습니다.
 */
public class 로또최고순위와최저순위 {
    public static void main(String[] args) {
        int[] lottos = {44, 1, 0, 0, 31, 25};
        int[] win_nums = {31, 10, 45, 1, 6, 19};
        System.out.println(Arrays.toString(solution(lottos, win_nums))); // [3, 5]
    }

    //나의 풀이 - 투 포인터 활용
    public static int[] solution(int[] lottos, int[] win_nums) {
        int zeroCount = 0;
        Arrays.sort(lottos); Arrays.sort(win_nums);
        int lot = 0; int win = 0;
        int correct = 0;
        while(lot<lottos.length){
            if(win==6) break;
            if(lottos[lot]==0){
                zeroCount++;
                lot++;
                continue;
            }
            if(lottos[lot]==win_nums[win]){
                correct++;
                win++;
                lot++;
            }
            else if(lottos[lot]<win_nums[win]){
                lot++;
            }
            else{
                win++;
            }
        }

        int[] answer = new int[2];
        int maxCorrect = correct+zeroCount;
        answer[1] = getRank(correct);
        answer[0] = getRank(maxCorrect);
        return answer;
    }
    public static int getRank(int num){
        return num <= 1 ? 6 : 7-num;
    }

    //Claude의 풀이 - HashSet 활용 (배열 길이가 6으로 고정이라 정렬/투포인터 없이도 충분함)
    public static int[] solutionByClaude(int[] lottos, int[] win_nums) {
        // win_nums를 HashSet에 담아, "당첨번호에 포함되는지"를 O(1)로 확인 가능하게 만듦
        Set<Integer> winSet = new HashSet<>();
        for (int num : win_nums) winSet.add(num);

        int zeroCount = 0; // 알아볼 수 없는 번호(0) 개수
        int correct = 0;   // 확실하게 맞은 번호 개수

        // lottos를 한 번만 순회 (정렬 불필요)
        for (int num : lottos) {
            if (num == 0) zeroCount++;
            else if (winSet.contains(num)) correct++;
        }

        // 최고 순위: 0을 전부 당첨번호로 가정 → correct + zeroCount개 맞음
        // 최저 순위: 0을 전부 당첨 안 된 번호로 가정 → correct개만 맞음
        return new int[]{getRank(correct + zeroCount), getRank(correct)};
    }

    //정렬 없이 Arrays.binarySearch 활용 가능
    public int[] solutionWithBinarySearch(int[] lottos, int[] win_nums) {
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        int answer = 0;
        int hidden = 0;

        Arrays.sort(win_nums);
        for (int i = 0; i < lottos.length; i++)
            if (Arrays.binarySearch(win_nums, lottos[i]) > -1)
                answer++;
            else if (lottos[i] == 0)
                hidden++;

        return new int[] {rank[answer + hidden], rank[answer]};
    }
}
