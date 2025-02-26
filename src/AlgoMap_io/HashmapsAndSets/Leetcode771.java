package AlgoMap_io.HashmapsAndSets;

import java.util.HashSet;
import java.util.Set;
/*
You're given strings jewels representing the types of stones that are jewels,
and stones representing the stones you have.
Each character in stones is a type of stone you have.
You want to know how many of the stones you have are also jewels.

Letters are case sensitive, so "a" is considered a different type of stone from "A".
 */
public class Leetcode771 {
    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA","aAAbbbb"));
    }
    public static int numJewelsInStones(String jewels, String stones){
        Set<Character> jewel = new HashSet<>();
        for(int i=0;i<jewels.length();i++){
            jewel.add(jewels.charAt(i));
        }
        int count=0;
        for(int j=0;j<stones.length();j++){
            if(jewel.contains(stones.charAt(j))){
                count++;
            }
        }
        return count;
    }
    public static int numJewelsInStones1s(String jewels, String stones) {
        int count=0;
        for(int i=0;i<jewels.length();i++){
            for(int j=0;j<stones.length();j++){
                if(jewels.charAt(i)==stones.charAt(j))
                    count+=1;

            }
        }
        return count;
    }
    public static int numJewelsInStones0s(String jewels, String stones) {
        int count = 0;
        for (int i = 0; i < jewels.length(); i++) {
            for (int j = 0; j < stones.length(); j++) {
                if(stones.charAt(j) == jewels.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }
}
/*
jewels.length와 stones.length가 최대 50으로 제한되어 있기 때문에,
중첩된 for 루프를 사용하는 numJewelsInStones1s와 numJewelsInStones0s 메서드가 실제로 더 빠를 수 있다.

이 경우, 입력 크기가 작기 때문에 HashSet을 사용하는 오버헤드가
중첩된 for 루프의 단순한 비교 연산보다 더 클 수 있다.
따라서, 입력 크기가 작을 때는 중첩된 for 루프가 더 빠르게 동작할 수 있다.

일반적으로는 HashSet을 사용하는 방법이 더 효율적이지만,
입력 크기가 작을 때는 중첩된 for 루프가 더 빠를 수 있다는 점을 고려해야 한다.
 */
