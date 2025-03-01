package AlgoMap_io.TwoPointers;
/*
A phrase is a palindrome if,
after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters,
it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 */
public class Leetcode125 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }
    public static boolean isPalindrome(String s) {
        if(s.isEmpty() || s.length()==1) return true;
        int left = 0, right = s.length()-1;
        while(left<right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            if(!((leftChar>='a'&&leftChar<='z')||(leftChar>='A'&&leftChar<='Z')||(leftChar>='0'&&leftChar<='9'))){
                left++; continue;
            }
            if(!((rightChar>='a'&&rightChar<='z')||(rightChar>='A'&&rightChar<='Z')||(rightChar>='0'&&rightChar<='9'))){
                right--; continue;
            }
            //!String.valueOf(leftChar).equalsIgnoreCase(String.valueOf(rightChar))
            if(!String.valueOf(leftChar).toLowerCase().equals(String.valueOf(rightChar).toLowerCase())){
                return false;
            }
            else{
                left++; right--;
            }
        }
        return true;
    }
    //노가다 버전
    public static boolean isPalindrome2(String s) {
        if(s.isEmpty() || s.length()==1) return true;
        int left = 0, right = s.length()-1;
        while(left<right){
            char leftChar = s.charAt(left);
            char rightChar = s.charAt(right);
            //!((leftChar 아니면 rightChar>='a'&&leftChar<='z')||(leftChar>='A'&&leftChar<='Z')) 틀림. 숫자도 가능함.
            if(!(Character.isLetterOrDigit(leftChar))){
                left++; continue;
            }
            if(!(Character.isLetterOrDigit(rightChar))){
                right--; continue;
            }
            //!String.valueOf(leftChar).equalsIgnoreCase(String.valueOf(rightChar))
            if(!String.valueOf(leftChar).toLowerCase().equals(String.valueOf(rightChar).toLowerCase())){
                return false;
            }
            else{
                left++; right--;
            }
        }
        return true;
    }
    //2ms 버전
    public static boolean isPalindrome3(String s) {
        int L = 0, R = s.length() - 1;
        while (L < R) {
            while (L < R && !Character.isLetterOrDigit(s.charAt(L))) {
                L++;
            }
            while (L < R && !Character.isLetterOrDigit(s.charAt(R))) {
                R--;
            }
            if (L < R && Character.toLowerCase(s.charAt(L)) != Character.toLowerCase(s.charAt(R))) {
                return false;
            }
            L++;
            R--;
        }
        return true;
    }
}
/*
isalnum 메서드는 자바에서 Character 클래스의 isLetterOrDigit 메서드를 사용하여 구현할 수 있다.
이 메서드는 주어진 문자가 알파벳 문자 또는 숫자인지 확인한다.
 */