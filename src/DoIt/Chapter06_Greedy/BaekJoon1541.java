package DoIt.Chapter06_Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String[] terms = str.split("-");
        int sum=stringSum(terms[0]);
        for(int i=1;i<terms.length;i++){
            sum-=stringSum(terms[i]);
        }
        System.out.println(sum);
    }
    static int stringSum(String str){
        int sum=0;
        String[] parts=str.split("\\+");
        for(String part : parts){
            sum+=Integer.parseInt(part);
        }
        return sum;
    }
}
