package Programmers.lv1;

import java.util.*;

/*
문제 설명

수포자는 수학을 포기한 사람의 준말입니다.
수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다. 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.

1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...

1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.


제한 조건

    시험은 최대 10,000 문제로 구성되어있습니다.
    문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
    가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.

 */
public class 모의고사 {
    public static void main(String[] args) {

    }

    //첫 시도 - 50/100
    //문제 잘못 읽고 쓸데 없이 전부 다 정렬하려다가 코드가 너무 복잡해진 버전
    public int[] solutionFailed(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};
        Map<Integer,Integer> correct = new HashMap<>();
        correct.put(1,0); correct.put(2,0); correct.put(3,0);
        //맞은 문제 수 비교
        for(int i=0;i<answers.length;i++){
            if(first[i%5]==answers[i]) correct.put(1,correct.getOrDefault(1,0)+1);
        }
        for(int i=0;i<answers.length;i++){
            if(second[i%8]==answers[i]) correct.put(2,correct.getOrDefault(2,0)+1);
        }
        for(int i=0;i<answers.length;i++){
            if(third[i%10]==answers[i]) correct.put(3,correct.getOrDefault(3,0)+1);
        }

        //맵을 value 순으로 정렬하는 방법!!
        List<Map.Entry<Integer,Integer>> sorted =
                correct.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .toList();

        //answer 포맷에 맞추기
        //- 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
        for(int i=0;i<sorted.size();i++){
            if(i>0&&sorted.get(i).getValue()<sorted.get(i-1).getValue()) break;
            int key = sorted.get(i).getKey();
            answer.add(key);
        }
        return answer.isEmpty() ? new int[]{1, 2, 3} : answer.stream().mapToInt(Integer::intValue).toArray();
    }

    //정렬 필요 없이 최고점을 구한 뒤에 최고점과 동일한지만 검사
    public int[] solution(int[] answers) {
        int[] answer = new int[3];
        int[] first = {1,2,3,4,5};
        int[] second = {2,1,2,3,2,4,2,5};
        int[] third = {3,3,1,1,2,2,4,4,5,5};
        Map<Integer,Integer> correct = new HashMap<>();
        correct.put(1,0); correct.put(2,0); correct.put(3,0);
        int max = 0;
        //맞은 문제 수 비교
        for(int i=0;i<answers.length;i++){
            if(first[i%5]==answers[i]) correct.put(1,correct.getOrDefault(1,0)+1);
        }
        //max = Math.max 사용하면 더 깔끔함.
        //max = Math.max(max, correct.get(1));
        if(correct.get(1)>max) max = correct.get(1);
        for(int i=0;i<answers.length;i++){
            if(second[i%8]==answers[i]) correct.put(2,correct.getOrDefault(2,0)+1);
        }
        if(correct.get(2)>max) max = correct.get(2);
        for(int i=0;i<answers.length;i++){
            if(third[i%10]==answers[i]) correct.put(3,correct.getOrDefault(3,0)+1);
        }
        if(correct.get(3)>max) max = correct.get(3);

        //max와 점수가 동일한 학생만 찾아서 array에 담기
        int count = 0;
        for(int i=0;i<correct.size();i++){
            if(correct.get(i+1)==max){
                answer[count++] = i+1;
            }
        }
        //배열 복사하는 법 Arrays.copyOfRange(start,end) end index 미포함
        return Arrays.copyOfRange(answer,0,count);
    }
    //처음에 for문 패턴까지는 너무 쉬웠는데 그 이후 최댓값 뽑아내는거에서
    // 1등부터 3등 전부 출력하는줄 알고 문제 잘못 접근했다가 꼬인 거 같다.

    /* <Claude의 조언>
    solution 버전은 방향은 맞는데, 한 가지 짚을 부분이 있어:
        Map<Integer,Integer> correct = new HashMap<>();
        correct.put(1,0); correct.put(2,0); correct.put(3,0);

    Map의 키가 1,2,3으로 고정되고 개수도 3개로 고정돼 있다.
    이런 경우엔 HashMap보다 int[] correct = new int[3];이 훨씬 자연스럽다.

    지금 코드에도 사실상 correct.get(i+1)처럼 인덱스 접근을 흉내내고 있는데,
    이건 배열을 Map으로 어렵게 돌려쓰는 셈이다.

    HashMap은 "키가 동적이거나 임의의 값일 때" 쓰는 도구지,
    지금처럼 키가 1/2/3으로 미리 다 알려진 경우엔 배열이 더 자연스럽다.
     */
    public int[] solutionFromClaude(int[] answers) {
        int[][] patterns = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };
        int[] correct = new int[3];
        int max = 0;

        //for문 3번 적는거보다 이중 for문이 더 깔끔한데...
        for (int p = 0; p < 3; p++) {
            for (int i = 0; i < answers.length; i++) {
                if (patterns[p][i % patterns[p].length] == answers[i]) correct[p]++;
            }
            max = Math.max(max, correct[p]);
        }

        List<Integer> answer = new ArrayList<>();
        for (int p = 0; p < 3; p++) {
            if (correct[p] == max) answer.add(p + 1);
        }

        //List<Integer>을 int[]로 변환하는 방법 기억하기
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
