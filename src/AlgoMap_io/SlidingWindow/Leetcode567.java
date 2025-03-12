package AlgoMap_io.SlidingWindow;

import java.util.Arrays;
import java.util.HashMap;

/*
Given two strings s1 and s2, return true if s2 contains a

of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.
 */
public class Leetcode567 {
    public static void main(String[] args) {
//        HashMap<Character,Integer> hm = new HashMap<>();
//        HashMap<Character,Integer> hm2 = new HashMap<>();
//        hm.put('A',1); hm2.put('A',1);
//        System.out.println(hm.size());
        System.out.println(checkInclusionWithArray("ab","eidbaooo"));
    }
    //HashMap을 이용한 내 풀이 - 실행시간 26ms
    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> maps1 = new HashMap<>();
        for(char c : s1.toCharArray()){
            maps1.put(c, maps1.getOrDefault(c, 0) + 1);
        }
        HashMap<Character, Integer> maps2 = new HashMap<>();
        int left = 0;
        for(int right=0;right<s2.length();right++){
            maps2.put(s2.charAt(right), maps2.getOrDefault(s2.charAt(right), 0) + 1);
            while(right-left+1>s1.length()){
                maps2.put(s2.charAt(left), maps2.get(s2.charAt(left)) - 1);
                if(maps2.get(s2.charAt(left)) == 0){
                    maps2.remove(s2.charAt(left));
                }
                left++;
            }
            if(maps1.equals(maps2)&&right-left+1==s1.length()){
                return true;
            }
        }
        return false;
    }
    //배열을 이용해 최적화한 버전 - 실행시간 6ms
    public static boolean checkInclusionWithArray(String s1, String s2) {
        int[] counts1 = new int[26];
        for(char c : s1.toCharArray()){
            counts1[c-'a']++;
        }
        int[] counts2 = new int[26];
        int left = 0;
        for(int right=0;right<s2.length();right++){
            counts2[s2.charAt(right)-'a']++;
            while(right-left+1>s1.length()){
                counts2[s2.charAt(left)-'a']--;
                left++;
            }
            if(Arrays.equals(counts1,counts2)){
                return true;
            }
        }
        return false;
    }
}
/*
슬라이딩 윈도우의 크기를 s1의 길이만큼 늘린 다음,
슬라이딩 윈도우를 한칸씩 옮기면서, 두 배열(해시맵)에 슬라이딩 윈도우에 속한 글자 개수를 기록한 뒤
동일하면 true 리턴, 아니면 false 리턴.
 */