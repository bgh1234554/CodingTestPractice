package AlgoMap_io.HashmapsAndSets;

import java.util.*;

/*
Given an array of strings strs, group the together. You can return the answer in any order.
 */
public class Leetcode49 {
    public static void main(String[] args) {
//        String str = "tea";
//        char[] chars = str.toCharArray();
//        Arrays.sort(str.toCharArray());
//        //이렇게 하면 주소만 나온다
//        //String sorted = Arrays.toString(chars);
//        String sorted = new String(chars);
//        System.out.println(sorted);
        System.out.println(groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }
    public static List<List<String>> groupAnagrams(String[] strs){
        Map<String, ArrayList<String>> map = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if(!map.containsKey(sorted)){
                map.put(sorted,new ArrayList<>());
            }
            map.get(sorted).add(str);
        }
        //map에 있는 모든 value들을 하나씩 추가하는 방법 기억해두기.
        //여기서는 for-each 문을 사용함.
        List<List<String>> ans = new ArrayList<>();
        for(ArrayList<String> lists:map.values()){
            ans.add(lists);
        }
        return ans;
    }
    public List<List<String>> groupAnagrams6s(String[] strs) {
        Map<String, List<String>> ans = new HashMap<>();

        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<>());
            }
            ans.get(key).add(s);
        }
        //for-each문 없이 바로 ArrayList에 values를 넣어서 리턴
        return new ArrayList<>(ans.values());
    }
}
/*
Time complexity: O(m∗nlogn)
Space complexity: O(m∗n)

Where mm is the number of strings and nn is the length of the longest string.
 */