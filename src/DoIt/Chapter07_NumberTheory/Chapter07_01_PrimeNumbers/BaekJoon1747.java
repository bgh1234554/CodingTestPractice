package DoIt.Chapter07_NumberTheory.Chapter07_01_PrimeNumbers;

import java.util.Scanner;

public class BaekJoon1747 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N : 1~1000000
        int[] nums = new int[10000001];
        //에라토스테네스의 체 구현하는 방법 기억해놓기.
        for(int i=2;i<nums.length;i++){
            nums[i]=i;
        }
        for(int i=2;i<Math.sqrt(nums.length);i++){
            if(nums[i]!=0){
                for (int j = i + i; j < nums.length; j += i) {
                    nums[j] = 0;
                }
            }
        }
        //N의 값의 범위가 10만인거지, 출력 값의 범위가 10만인 것이 아니기 때문에,
        //소수의 범위를 더 구해놓아야 한다.
        //문제를 꼼꼼히 읽어야겠다.
        for(int i=N;i<=10000000;i++){
            if(nums[i]!=0&&isPalindrome(Integer.toString(nums[i]))){
                System.out.println(nums[i]);
                break;
            }
        }
    }
    //String으로 안만들면 10201을 Palindrome 검사할때 String을 int로 재변환하면
    //'020' 문자열이 정수 20으로 바뀌기 때문이다.
    private static boolean isPalindrome(String num) {
        if(num.length()<=1){
            return true;
        }
        if(num.charAt(0)!= num.charAt(num.length()-1)){
            return false;
        }
        return isPalindrome(num.substring(1, num.length()-1));
    }
}
/*
팰린드롬 함수 자체의 구현은 예전부터 많이 해봤는데, 입력값이 어떤 형식으로 들어오냐에 따라
어떻게 처리해야 하는지가 중요하다. String의 팰린드롬을 제일 흔하게 구현하기 때문에,
처음에 int를 변수로 받았다가, 그냥 String으로 바꾼 다음에 함수에 넣는 방식으로 구현하였다.
또 위에 적어놨듯이 N의 범위가 10만까지인 것이지,
출력값의 범위가 10만인 것이 아니기 때문에, 숫자 배열의 크기를 더 늘려줘야 한다.
N이 10만일 때 정답은 1003001이기 때문에, 배열 크기가 최소 1003002는 되어야 한다.
 */
