package AlgoNap_io.ArraysAndString;
/*
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Roman numerals are usually written largest to smallest from left to right.
However, the numeral for four is not IIII. Instead, the number four is written as IV.
Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX.
There are six instances where subtraction is used:

    I can be placed before V (5) and X (10) to make 4 and 9.
    X can be placed before L (50) and C (100) to make 40 and 90.
    C can be placed before D (500) and M (1000) to make 400 and 900.

Given a roman numeral, convert it to an integer.
 */

public class Leetcode13 {
    public static void main(String[] args) {
        Leetcode13 sol = new Leetcode13();
        System.out.println(sol.romanToInt("MCMXCIV"));
    }
    public int romanToInt(String s) {
        int sum=0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='I'){
                if(i<s.length()-1&&(s.charAt(i+1)=='V'||s.charAt(i+1)=='X')){
                    sum--;
                }
                else{sum++;}
            }
            else if(s.charAt(i)=='V'){
                sum+=5;
            }
            else if(s.charAt(i)=='X'){
                if(i<s.length()-1&&(s.charAt(i+1)=='L'||s.charAt(i+1)=='C')){
                    sum-=10;
                }
                else{
                    sum+=10;
                }
            }
            else if(s.charAt(i)=='L'){
                sum+=50;
            }
            else if(s.charAt(i)=='C'){
                if(i<s.length()-1&&(s.charAt(i+1)=='D'||s.charAt(i+1)=='M')){
                    sum-=100;
                }
                else{
                    sum+=100;
                }
            }
            else if(s.charAt(i)=='D'){
                sum+=500;
            }
            else if(s.charAt(i)=='M'){
                sum+=1000;
            }
        }
        return sum;
    }
}
