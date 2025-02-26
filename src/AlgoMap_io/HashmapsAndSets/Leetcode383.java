package AlgoMap_io.HashmapsAndSets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
Given two strings ransomNote and magazine,
return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

1 <= ransomNote.length, magazine.length <= 100000
 */
public class Leetcode383 {
    public static void main(String[] args) {
        System.out.println(canConstruct("aa","aab"));
    }
    public static boolean canConstruct(String ransomNote, String magazine){
        Map<Character,Integer> count = new HashMap<>();
        //getOrDefault 메서드 알아두기
        for(char c: magazine.toCharArray()){
            //초기화 안되어있으면 defaultValue로 초기화하는 메서드
            //.getOrDefault(key,defaultValue)
            count.put(c,count.getOrDefault(c,0)+1);
        }
        //나머지 로직은 동일
        for (char ch : ransomNote.toCharArray()) {
            if (count.getOrDefault(ch, 0) > 0) {
                count.put(ch, count.get(ch) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean canConstructWithArray(String ransomNote, String magazine){
        int[] count = new int[26];
        for(int i=0;i<magazine.length();i++){
            count[magazine.charAt(i)-'a']++;
        }
        for(int j=0;j<ransomNote.length();j++){
            if(count[ransomNote.charAt(j)-'a']==0){
                return false;
            }
            else{
                count[ransomNote.charAt(j)-'a']--;
            }
        }
        return true;
    }
    //or for(char c:magazine.toCharArray())
    //향상된 for문을 사용하면 1초대로 줄일 수 있다.
    public boolean canConstruct1s(String ransomNote, String magazine) {
        int[] count = new int[26];
        for(char c:magazine.toCharArray()){
            count[c-'a']++;
        }
        for(char c:ransomNote.toCharArray()){
            if(count[c-'a']==0){
                return false;
            }
            count[c-'a']--;
        }
        return true;
    }
}
/*
canConstruct 메서드가 가장 느린 이유는 HashMap을 사용하기 때문이다.
HashMap의 put 및 getOrDefault 메서드는 평균적으로 O(1) 시간 복잡도를 가지지만,
실제로는 해시 충돌이나 리사이징 등의 오버헤드가 발생할 수 있습니다.
또한, HashMap을 초기화하고 관리하는 데 추가적인 메모리와 시간이 소요됩니다.

반면에 canConstructWithArray와 canConstruct1s 메서드는 고정 크기의 배열을 사용하여
문자의 빈도를 저장하고 조회합니다. 배열 접근은 O(1) 시간 복잡도를 가지며,
추가적인 오버헤드가 거의 없습니다. 따라서, 배열을 사용하는 방법이 더 빠르게 동작합니다

시간복잡도는 둘 다 O(ransomNote.length+magazine.length)이나,
HashMap의 오버헤드로 인해 실제 실행시간은 더 느릴 수 있다.
 */