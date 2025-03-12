package AlgoMap_io.SlidingWindow;

import java.util.HashSet;
/*
Given a string s, find the length of the longest without duplicate characters.
 */
public class Leetcode3 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
    //직접만든 풀이 - 실행시간 3ms
    public static int lengthOfLongestSubstring(String s) {
        int start = 0; int maxLength = 0;
        //int[] visited = new int[26]; 알파벳만 있으면 상관없는데, 다른것도 포함이라...
        HashSet<Character> visited = new HashSet<>();
        for(int i=0;i<s.length();i++){
            if(!visited.add(s.charAt(i))){
                while(s.charAt(start)!=s.charAt(i)){
                    visited.remove(s.charAt(start));
                    start++;
                }
                //앞쪽에 있는거 추가해야 하니까 한번더 start++;
                start++;
            }
            maxLength = Math.max(maxLength, i - start + 1);
        }
        return maxLength;
    }
    //배열 활용 - 실행시간 1ms
    public int lengthOfLongestSubstringWithArray(String s) {
        int[] charIndex = new int[128]; // ASCII 문자는 128개이기 때문에 128개의 배열로 설정
        //각 문자의 최근 목격(?) 위치를 저장한다.
        int left = 0, maxx = 0;

        for (int right = 0; right < s.length(); right++) {
            char current = s.charAt(right);

            // 이미 본 경우에는 left 포인터(start 포인터)를 이동시킨다.
            if (charIndex[current] > 0) {
                left = Math.max(left, charIndex[current]);
            }

            // 현재 문자에 대한 목격위치를 저장
            //right가 아닌 right+1로 저장하는 이유는, 초기화가 0으로 되어있으니까,
            //right가 0일때 충돌이 발생할 수 있기 때문이다.
            //+1을 하니까, left 포인터를 옮길 때, 굳이 +1을 하지 않아도 된다는 장점도 있다.
            charIndex[current] = right + 1;

            // 다 끝났으면 길이 업데이트
            maxx = Math.max(maxx, right - left + 1);
        }

        return maxx;
    }
}
/*
이전에 풀어본 문제인 1004번 문제의 아류작 느낌? 1004번을 이미 풀어서 그런지 훨씬 쉽게 느껴진다.
 */
