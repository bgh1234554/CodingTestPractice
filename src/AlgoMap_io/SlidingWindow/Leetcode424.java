package AlgoMap_io.SlidingWindow;

import java.util.HashSet;
/*
You are given a string s and an integer k.
You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.
 */
public class Leetcode424 {
    public static void main(String[] args) {
        //System.out.println((char)('A'+1)); //'B' 리턴
        System.out.println(characterReplacement("AABABBA",1)); // 4
        System.out.println(characterReplacementAlgomapIo("AXBAAVAC",1));
    }
    //내가 직접 1004번 문제를 응용해서 푼 방법. 느림 - 실행시간 41ms
    public static int characterReplacement(String s, int k) {
        int maxLength = 0;
        for(int i=0;i<26;i++){
            char letter = (char)('A'+i);
            int lettercount = 0;
            int left = 0;
            for(int right = 0;right<s.length();right++){
                if(s.charAt(right)!=letter){
                    lettercount++;
                }
                while(lettercount>k){
                    if(s.charAt(left)!=letter){
                        lettercount--;
                    }
                    left++;
                }
                maxLength = Math.max(maxLength,right-left+1);
            }
        }
        return maxLength;
    }
    //첫번째 for문이 적게 실행되도록 HashSet 이요 - 19ms까지 줄임
    public static int characterReplacementOptimized(String s, int k) {
        int maxLength = 0;
        HashSet<Character> set = new HashSet<Character>();
        for(int i=0;i<s.length();i++){
            char letter = s.charAt(i);
            if(!set.add(letter)){
                continue;
            }
            int lettercount = 0;
            int left = 0;
            for(int right = 0;right<s.length();right++){
                if(s.charAt(right)!=letter){
                    lettercount++;
                }
                while(lettercount>k){
                    if(s.charAt(left)!=letter){
                        lettercount--;
                    }
                    left++;
                }
                maxLength = Math.max(maxLength,right-left+1);
            }
        }
        return maxLength;
    }
    //Algomap.io의 풀이 - 실행시간 6ms
    public static int characterReplacementAlgomapIo(String s, int k) {
        int[] counts = new int[26]; //글자 별 출현 수
        int l = 0, longest = 0;
        int maxCount = 0;

        for (int r = 0; r < s.length(); r++) {
            //가장 많이 출현한 글자의 수 (무슨 글자인지는 계속 유동적으로 바뀐다)
            maxCount = Math.max(maxCount, ++counts[s.charAt(r) - 'A']);

            //(r-l+1) - maxCount : 총 대체해야하는 글자 수
            while ((r - l + 1) - maxCount > k) {
                //새롭게 문자열 업데이트
                counts[s.charAt(l) - 'A']--;
                l++;
            }

            longest = Math.max(longest, r - l + 1);
        }

        return longest;
    }
}
