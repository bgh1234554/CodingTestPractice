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
