package DoIt.Chapter09_Tree.Chapter09_02_Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tNode root = new tNode(); //문자열 저장할 트리 생성
        while(N>0){
            String text = br.readLine();
            tNode now = root; //루트 트리에서부터 차근차근 시작하기.
            for(int i=0;i<text.length();i++){
                char c = text.charAt(i);
                //아직 액세스되지 않았으면 새롭게 노드 만들기
                if(now.next[c-'a']==null){
                    now.next[c-'a']=new tNode();
                }
                //이후 자식 노드로 접근하기
                now = now.next[c-'a'];
                //맨 끝에 도달하면 끝이라고 알려주도록 isEnd 값을 true로 만들기
                if(i==text.length()-1){
                    now.isEnd=true;
                }
            }
            N--;
        }
        int count = 0;
        while(M>0){
            String text = br.readLine();
            //루트 노드 탐색
            tNode now = root;
            for(int i=0;i<text.length();i++){
                char c = text.charAt(i);
                //없는 문자가 존재하면 더 이상 이 String을 탐색할 필요가 없으니 break
                if(now.next[c-'a']==null) break;
                now=now.next[c-'a'];
                //끝까지 다 탐색했으면 count++
                if(i==text.length()-1&&now.isEnd){
                    count++;
                }
            }
            M--;
        }
        System.out.println(count);
    }
    static class tNode{
        public tNode[] next = new tNode[26]; //알파벳이 26개니까.
        public boolean isEnd;
    }
}
/*
문자열의 트리인 트라이를 응용한 문제.
문자열 트리는 처음 구현해봐서 생소한 느낌이 있다.
 */