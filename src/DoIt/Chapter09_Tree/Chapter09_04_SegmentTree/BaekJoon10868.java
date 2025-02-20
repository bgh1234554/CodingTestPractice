package DoIt.Chapter09_Tree.Chapter09_04_SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon10868 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int leafStart = (int) Math.pow(2,Math.ceil(Math.log(N)/Math.log(2)));
        int treesize = leafStart *2;
        tree = new long[treesize];
        for(int i=1;i<leafStart;i++){
            tree[i]=Long.MAX_VALUE;
        }
        for(int i = leafStart;i<leafStart+N;i++){
            tree[i]=Long.parseLong(br.readLine());
        }
        setMinTree(treesize-1);
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            start += leafStart-1;
            end += leafStart-1;
            System.out.println(findMin(start,end));
        }
    }

    private static long findMin(int start, int end) {
        long min = Long.MAX_VALUE;
        while(start<=end){
            if(start%2==1){
                min = Math.min(min,tree[start]);
                start++;
            }
            if(end%2==0){
                min = Math.min(min,tree[end]);
                end--;
            }
            start/=2; end/=2;
        }
        return min;
    }

    private static void setMinTree(int i) {
        while(i!=1){
            tree[i/2]=Math.min(tree[i/2],tree[i]);
            i--;
        }
    }
}
//2042번과 사실상 동일한 세그먼트 트리 연습문제.