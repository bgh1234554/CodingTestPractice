package AlgoMap_io.ArraysAndString;

/*
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:

Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
 */

public class Leetcode14 {
    public static void main(String[] args) {
        Leetcode14 sol = new Leetcode14();
        String[] strs = {"flower","flow","flight"};
        System.out.println(sol.longestCommonPrefix(strs));
    }
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int min = 200;
        for(int i=0;i<strs.length;i++){
            min=Math.min(min,strs[i].length());
        }
        for(int i=0;i<min;i++){
            boolean common = true;
            for(int j=0;j<strs.length;j++){
                if (strs[0].charAt(i) != strs[j].charAt(i)) {
                    common = false;
                    break;
                }
            }
            if(common){ sb.append(strs[0].charAt(i)); }
            else{
                break;
            }
        }
        return sb.toString();
    }
}
