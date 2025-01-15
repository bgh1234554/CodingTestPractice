package AlgoNap_io.ArraysAndString;

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
