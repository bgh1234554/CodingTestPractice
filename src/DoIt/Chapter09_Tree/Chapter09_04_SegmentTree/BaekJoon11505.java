package DoIt.Chapter09_Tree.Chapter09_04_SegmentTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11505 {
    static long[] tree;
    static long DIVISOR = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int leafStart = (int) Math.pow(2,Math.ceil(Math.log(N)/Math.log(2)));
        int treesize = leafStart*2;
        tree = new long[treesize];
        for(int i=1;i<treesize;i++){
            tree[i]=1;
        }
        for(int i=leafStart;i<leafStart+N;i++){
            //여긴 원본 데이터를 받아야하나...?
            tree[i]=Long.parseLong(br.readLine());
        }
        setProductTree(treesize-1);
        for(int i=0;i<M+K;i++){
            st = new StringTokenizer(br.readLine());
            int mode = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            if(mode==1){
                update(start+leafStart-1,end);
            }
            else if(mode==2){
                start+=leafStart-1;
                end+=leafStart-1;
                System.out.println(partialProduct(start,end));
            }else{
                return;
            }
        }
    }

    private static long partialProduct(int start, long end) {
        long product=1;
        while(start<=end){
            if(start%2==1){
                product=product*tree[start]%DIVISOR;
                start++;
            }
            if(end%2==0){
                product=product*tree[(int) end]%DIVISOR;
                end--;
            }
            start/=2; end/=2;
        }
        return product;
    }

    private static void update(int index, long val) {
        tree[index]=val;
        while(index>1){
            index/=2;
            //곱하기 연산과 나머지 연산의 우선순위가 동등하기 때문에, 괄호를 붙여서 계산하면
            //마지막에 한번 더 나머지 연산을 하지 않으면 오버플로우가 발생한다.
            //tree[index * 2] % DIVISOR * tree[index * 2 + 1] % DIVISOR과 같이 연산하면,
            //마지막에 나머지 연산을 수행하기 때문에 오버플로우가 발생하지 않는다.
            tree[index]=(tree[index*2]%DIVISOR)*(tree[index*2+1]%DIVISOR)%DIVISOR;
        }
    }

    private static void setProductTree(int i) {
        while(i!=1){
            tree[i/2]=tree[i/2]*tree[i]%DIVISOR;
            i--;
        }
    }
}
