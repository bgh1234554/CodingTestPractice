package Etc;

import java.util.Scanner;

public class BaekJoon2941 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int cnt=0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='c'){
                if(i<str.length()-1&&(str.charAt(i+1)=='='||str.charAt(i+1)=='-')){
                    i++;
                }
                cnt++;
            }
            else if(str.charAt(i)=='d'){
                if(i<str.length()-1&&(str.charAt(i+1)=='-')){
                    i++;
                }
                else if(i<str.length()-2&&str.charAt(i+1)=='z'&&str.charAt(i+2)=='='){
                    i=i+2;
                }
                cnt++;
            }
            else if(str.charAt(i)=='l'){
                if(i<str.length()-1&&str.charAt(i+1)=='j'){
                    i++;
                }
                cnt++;
            }
            else if(str.charAt(i)=='n'){
                if(i<str.length()-1&&str.charAt(i+1)=='j'){
                    i++;
                }
                cnt++;
            }
            else if(str.charAt(i)=='s'){
                if(i<str.length()-1&&str.charAt(i+1)=='='){
                    i++;
                }
                cnt++;
            }
            else if(str.charAt(i)=='z'){
                if(i<str.length()-1&&str.charAt(i+1)=='='){
                    i++;
                }
                cnt++;
            }
            else{
                cnt++;
            }
        }
        System.out.println(cnt);
        //아니면 String의 replace함수를 이용해서 여러 글자를 하나로 바꿔도 된다.
    }
}
