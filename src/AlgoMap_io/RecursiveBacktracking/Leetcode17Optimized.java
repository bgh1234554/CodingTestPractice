package AlgoMap_io.RecursiveBacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//HashMap에서 get연산을 하는데 자꾸 NullPointerException이 나온다.
//key값을 집어넣을 때 charAt 메서드 호출 후 int로 형변환을 하지 않아 1차 오류
//(int) digits.charAt(i)가 아니라 digits.charAt(i)-'0'을 해야 된다.
public class Leetcode17Optimized {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
    //String 대신 StringBuilder 사용 - 실행시간 5ms
    static List<String> res;
    static StringBuilder sol;
    //int 형변환하기 귀찮으니까 Character을 key로 지정하기
    static HashMap<Character,String> map = new HashMap<>();
    public static List<String> letterCombinations(String digits){
        map.put('2',"abc"); map.put('3',"def"); map.put('4',"ghi");
        map.put('5',"jkl"); map.put('6',"mno"); map.put('7',"pqrs");
        map.put('8',"tuv"); map.put('9',"wxyz");
        if(digits.isEmpty()){return new ArrayList<>();}
        res = new ArrayList<>();
        //char을 int로 바꿔서 get에 넣어야 하니까
        for(int i = 0; i<map.get(digits.charAt(0)).length(); i++){
            sol=new StringBuilder().append(map.get(digits.charAt(0)).charAt(i));
            backtrack(digits,1);
        }
        return res;
    }
    public static void backtrack(String digits, int start) {
        if(start == digits.length()){
            res.add(sol.toString());
            return;
        }
        for(int i=0;i<map.get(digits.charAt(start)).length();i++){
            sol.append(map.get(digits.charAt(start)).charAt(i));
            backtrack(digits,start+1);
            sol.deleteCharAt(sol.length()-1);
        }
    }
}
