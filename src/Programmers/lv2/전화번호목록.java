package Programmers.lv2;

import java.util.*;

/*
문제 설명

전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

    구조대 : 119
    박준영 : 97 674 223
    지영석 : 11 9552 4421

전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때,
어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

제한 사항

    phone_book의 길이는 1 이상 1,000,000 이하입니다.
        각 전화번호의 길이는 1 이상 20 이하입니다.
        같은 전화번호가 중복해서 들어있지 않습니다.

 */
public class 전화번호목록 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"119", "97674223", "1195524421"}));
    }

    //내가 푼 풀이
    public static boolean solution(String[] phone_book){
        Arrays.sort(phone_book); //짧은게 앞에 있어야 하니까
        HashMap<String, String> map = new HashMap<>(); //Array 대신 빨리 찾기 위해 Map으로
        for(String phone : phone_book){
            for(int i = 0; i < phone.length(); i++){
                String prefix = phone.substring(0, i);
                if(map.containsKey(prefix)){
                    return false;
                }
            }
            map.put(phone, phone);
        }
        return true;
    }

    //정렬을 쓸거면 굳이 HashMap을 쓸 필요가 없음
    public static boolean solutionWithoutMap(String[] phone_book) {
        Arrays.sort(phone_book);
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) { //String의 startsWith 함수 꼭 기억하기
                return false;
            }
        }
        return true;
    }

    //정렬 없이 풀기
    public static boolean solutionWithSet(String[] phone_book) {
        //일단 미리 번호를 전부 넣어놓기 어차피 String,String이라 Map 필요 없음.
        Set<String> numberSet = new HashSet<>(Arrays.asList(phone_book));

        for (String phone : phone_book) {
            for (int i = 1; i < phone.length(); i++) {
                String prefix = phone.substring(0, i);
                if (numberSet.contains(prefix)) {
                    return false;
                }
            }
        }
        return true;
    }

    //87.5 / 100
    //실패 원인: 짧은 번호가 나중에 등장하는 경우를 못 잡음
    //(map에는 "지금까지 처리한" 번호만 들어있어서, 순서에 따라 접두사 관계를 놓칠 수 있음)
    //예: ["1195524421", "119"] 순서로 들어오면 119가 접두사인 걸 못 잡아냄
    public static boolean solutionFailed(String[] phone_book){
        HashMap<String, String> map = new HashMap<>(); //Array 대신 빨리 찾기 위해 Map으로
        for(String phone : phone_book){
            for(int i = 0; i < phone.length(); i++){
                String prefix = phone.substring(0, i);
                if(map.containsKey(prefix)){
                    return false;
                }
            }
            map.put(phone, phone);
        }
        return true;
    }

    //79.2 / 100
    //바깥 루프 안에서 list.sort()를 매번 호출 → 최악의 경우 O(n^2 log n)에 가까운 시간 소요
    //phone_book 길이가 최대 100만이라 이 정도 복잡도는 시간제한 안에 못 들어옴
    public static boolean solutionFailed2(String[] phone_book) {
        ArrayList<String> list = new ArrayList<>();
        for(String phone : phone_book){
            list.sort((o1, o2) -> o1.length() - o2.length());
            for(String target : list){
                if(phone.startsWith(target)){ //startsWith 기억하기!!
                    return false;
                }
            }
            list.add(phone);
        }
        return true;
    }
}
/*
[이번 문제 정리]
- 접두사 관계 검사는 "지금까지 본 것"만 기준으로 하면 순서에 따라 놓칠 수 있다
  → 전체 데이터를 먼저 확보한 뒤(정렬 or Set) 검사해야 순서 무관하게 정확해짐
- 정렬(Arrays.sort) vs Set(HashSet): 둘 다 정답이지만,
  정렬은 인접 비교만 하면 되고 문자열 객체 생성이 적어 대용량(최대 100만)에서 더 유리
- 반복문 안에서 매번 재정렬(list.sort())하면 정확해도 시간초과 날 수 있음 — 정렬은 한 번만
*/