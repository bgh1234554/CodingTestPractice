package DoIt.Chapter03_DataStructure.Chapter03_1_ArraysAndList;

import java.io.*;

public class BaekJoon11720 { //채점시에는 Main으로 수정해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int sum=0;
        for(int i=0;i<str.length();i++){
            sum+=Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        sb.append(sum);
        System.out.println(sb);
        //str을 받은 다음에 .toCharArray 메서드를 통해 char[]로 변환 후,
        //똑같이 for문 돌면서 더해도 된다.
    }
}
