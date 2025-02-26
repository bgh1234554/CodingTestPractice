package AlgoMap_io.HashmapsAndSets;

import java.util.Arrays;
import java.util.HashMap;

/*
Given two strings s and t, return true if t is an of s, and false otherwise.
 */
public class Leetcode242 {
    public static void main(String[] args) {
        System.out.println(isAnagram("bat","tab"));
    }
    //내가 푼 버전. 정렬 후 데이터가 동일한지 확인한다.
    public static boolean isAnagram(String s,String t){
        char[] chars = s.toCharArray();
        char[] chart = t.toCharArray();
        Arrays.sort(chars); Arrays.sort(chart);
        //두 배열의 내용이 동등한지 비교할때는 Object의 equals 메서드가 아닌,
        //Arrays.equals 메서드를 사용해야 한다.
        return Arrays.equals(chars, chart);
    }
    //Map을 이용한 버전. 당연히 더 느리다.
    public static boolean isAnagramWithMaps(String s,String t){
        HashMap<Character,Integer> sMap = new HashMap<>();
        HashMap<Character,Integer> tMap = new HashMap<>();
        for(char c:s.toCharArray()){
            sMap.put(c,sMap.getOrDefault(c,0)+1);
        }
        for(char c:t.toCharArray()){
            tMap.put(c,tMap.getOrDefault(c,0)+1);
        }
        return sMap.equals(tMap);
    }

    //단순히 알파벳 배열을 2개를 만들어서 개수를 비교해도 가능하다.
    public boolean isAnagram1sec(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int arr[]=new int[26];
        for(char ch:s.toCharArray()){
            arr[ch-'a']++;
        }
        int arr1[]=new int[26];
        for(char ch:t.toCharArray()){
            arr1[ch-'a']++;
        }
        for(int i=0;i<26;i++){
            if(arr[i]!=arr1[i])return false;
        }
        return true;
    }
}
