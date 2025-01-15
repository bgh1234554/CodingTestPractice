package AlgoNap_io.ArraysAndString;

/*
You are given two strings word1 and word2.
Merge the strings by adding letters in alternating order, starting with word1.
If a string is longer than the other, append the additional letters onto the end of the merged string.
*/

public class Leetcode1768 {
    public static void main(String[] args) {
        Leetcode1768 sol = new Leetcode1768();
        System.out.println(sol.mergeAlternately("abc","pqrs"));
    }
    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int minlength = Math.min(word1.length(),word2.length());
        for(int i=0;i<minlength;i++){
            sb.append(word1.charAt(i)).append(word2.charAt(i));
        }
        if(word1.length()>word2.length()){
            sb.append(word1.substring(minlength));
        }
        else if(word2.length()>word1.length()){
            sb.append(word2.substring(minlength));
        }
        return sb.toString();
    }
}
