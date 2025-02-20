package DoIt.Chapter09_Tree.Chapter09_04_SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2042 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        //Math.log는 밑이 e이기 때문에, log base 2가 필요해서 밑변환 공식을 사용했다.
        //leaftStart = 리프 노드의 시작 인덱스
        int leafStart = (int) Math.pow(2,Math.ceil(Math.log(N)/Math.log(2)));
        //트리의 크기
        int treesize = leafStart*2;
        tree = new long[treesize];
        //리프 노드 입력 받기
        for(int i=leafStart;i<leafStart+N;i++){
            tree[i]=Long.parseLong(br.readLine());
        }
        setTree(treesize-1); //트리 생성 함수
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            if(mode==1){
                changeVal(start+leafStart-1,end);
            }
            else if(mode==2){
                //질의를 세그먼틑 트리의 인덱스에 맞게 변환
                start += leafStart-1;
                end += leafStart-1;
                System.out.println(partialSum(start,end));
            }
            else{
                return;
            }
        }
    }
    //새롭게 변경된 노드의 값에 맞춰 세그먼트 트리 업데이트
    private static void changeVal(int index, long val) {
        long diff = val-tree[index];
        while(index>0){
            tree[index]+=diff;
            index/=2; //부모 노드로 한칸씩 올라간다.
        }
    }

    //구간 합 구하는 함수. 위로 올라가면서 선택한 노드를 sum에 더한다.
    private static long partialSum(int start, long end) {
        long sum = 0;
        while(start<=end){
            if(start%2==1){
                sum+=tree[start];
                start++;
            }
            if(end%2==0){
                sum+=tree[(int) end];
                end--;
            }
            start/=2;end/=2;
        }
        return sum;
    }

    //자식 노드의 변경된 값을 바탕으로 i번째 인덱스부터 탐색해 부모 노드 초기화
    private static void setTree(int i) {
        while(i!=1){
            tree[i/2]+=tree[i];
            i--;
        }
    }
}
