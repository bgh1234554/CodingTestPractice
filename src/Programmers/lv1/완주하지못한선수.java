package Programmers.lv1;

import java.util.Arrays;
import java.util.HashMap;

/*
문제 설명
    수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
    마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때,
    완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

제한사항
    마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
    completion의 길이는 participant의 길이보다 1 작습니다.
    참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
    참가자 중에는 동명이인이 있을 수 있습니다.
 */
public class 완주하지못한선수 {
    static void main() {
        String[] participant = {"leo","kiki","eden"};
        String[] completion = {"eden","kiki"};
        System.out.println(solution(participant,completion)); //leo
    }
    /*
    정렬로 풀기
    둘이 이름 순으로 정렬하면, 무조건 participant가 1장 많을거니까,
    participant - O X R
    completion - O R
    이면 for loop으로 completion에 맞춰서 돌리면 둘이 안맞을때 participant가 완주 못한거니까.

    for loop 다 돌면 participant 맨 마지막 선수가 완주 실패한것.
    -> 동명이인 조건을 알지 않아도 풀 수 있었다. 정렬하면 자동으로 풀리니까.
     */
    public static String solution(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);
        for(int i=0;i<completion.length;i++){
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
        return participant[participant.length-1];
    }

    /*
    면접에서 설명하기 편한, 동명이인 조건을 고려했다는 것을 보여줄 수 있는 HashMap 스타일 풀이.
     */
    public static String solutionWithHashMap(String[] participant, String[] completion) {
        //명시적 동명이인 처리 -> 이름 별 참가 인원 수
        HashMap<String, Integer> map = new HashMap<>();
        for(String name:completion){
            map.put(name, map.getOrDefault(name,0)+1);
        }

        //participation에서 하나씩 빼기
        for(String p:participant){
            if(!map.containsKey(p)||map.get(p)==0){
                return p;
            }
            map.put(p,map.get(p)-1);
        }

        return null;
    }
}
