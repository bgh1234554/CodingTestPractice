package AlgoMap_io.BitManipulation;

/*
Given an integer num, return a string of its base 7 representation.
 */
public class Leetcode504 {
    public static void main(String[] args) {

    }
    //자바 내장 메서드를 사용하는 방법
    public String convertToBase7Radix(int num) {
        return Integer.toString(num,7);
    }
    public String covertToBase7(int num){
        StringBuilder sb = new StringBuilder();
        int copy = Math.abs(num);
        //그 나누기 일일이 하는 걸 직접 구현한 것이라고 보면 됨
        while(copy>0){
            sb.append(copy%7);
            copy/=7;
        }
        //음수면 마지막에 - 붙여야 하니까
        if(num<0) sb.append("-");
        return sb.reverse().toString();
    }
}
