package AlgoNap_io.ArraysAndString;

public class Leetcode392 {
    public static void main(String[] args) {
        Leetcode392 sol = new Leetcode392();
        sol.isSubsequence("aba","abaza");
    }
    public boolean isSubsequence(String s, String t) {
        int i=0;int j=0;
        while(true){
            if(s.length()==0||t.length()==0){
                break;
            }
            if(s.charAt(i)==t.charAt(j)){
                i++; j++;
            }
            else{
                j++;
            }
            if(i==s.length()||j==t.length()){
                break;
            }
        }
        return i==s.length();
    }
}
