package AlgoMap_io.ArraysAndString;

import java.util.Arrays;

/*
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R

And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);

 */
public class Leetcode6 {
    public static void main(String[] args) {
        System.out.println(convert("AB",1));
        System.out.println(convert("PAYPALISHIRING",4));
    }
    public static String convert(String s, int numRows){
        if(numRows==1) return s;
        String[] strs = new String[numRows];
        Arrays.fill(strs, "");
        int pos = 0; int direction = -1;
        for(int i=0;i<s.length();i++){
            strs[pos]+=String.valueOf(s.charAt(i));
            if(pos==numRows-1||pos==0){
                direction*=-1;
                pos+=direction;
            }
            else{
                pos+=direction;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String str: strs){
            sb.append(str);
        }
        return String.valueOf(sb);
    }
}
