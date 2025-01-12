package DoIt.Chapter03_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon12981 {
    static int[] require = new int[4];
    static int[] state= new int[4];
    static int condition=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(st.nextToken());
        String dna = br.readLine();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            require[i]=Integer.parseInt(st.nextToken());
            if(require[i]==0){
                condition++; //0개이상이란건 무조건 만족한다는 뜻이니까.
            }
        }
        for(int i=0;i<len;i++){
            add(dna.charAt(i));
        }
        int start=0, end=len-1, cnt=0;
        while(true){
            if(condition==4){
                cnt++;
            }
            end++;
            if(end==size) break;
            add(dna.charAt(end)); //늘리고 조건 만족하는지 검사.
            remove(dna.charAt(start)); //지웠을때 조건 만족안하면 condition--
            start++;
        }
        System.out.println(cnt);
    }
    public static void add(char c){
        if (c == 'A') {
            state[0]++;
            if(state[0]==require[0]){
                condition++;
            }
        }
        else if (c == 'C') {
            state[1]++;
            if(state[1]==require[1]){
                condition++;
            }
        }
        else if (c == 'G') {
            state[2]++;
            if(state[2]==require[2]){
                condition++;
            }
        }
        else if (c == 'T') {
            state[3]++;
            if(state[3]==require[3]){
                condition++;
            }
        }
    }
    public static void remove(char c){
        if (c == 'A') {
            if(state[0]==require[0]){
                condition--;
            }
            state[0]--;
        }
        else if (c == 'C') {
            if(state[1]==require[1]){
                condition--;
            }
            state[1]--;
        }
        else if (c == 'G') {
            if(state[2]==require[2]){
                condition--;
            }
            state[2]--;
        }
        else if (c == 'T') {
            if(state[3]==require[3]){
                condition--;
            }
            state[3]--;
        }
    }
}
/*
4 2
GATA
1 0 0 1
x개 "이상"이라는 포인트를 까먹었다.
이상이니까 조건이 0이면 무조건 만족하니까 condition++하면 된다. 나머지는 차근차근 생각하면 된다.
0일때 케이스 처리때문에 애먹었다.
 */
