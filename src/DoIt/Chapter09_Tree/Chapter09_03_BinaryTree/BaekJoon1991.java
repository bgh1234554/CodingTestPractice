package DoIt.Chapter09_Tree.Chapter09_03_BinaryTree;

import java.util.Scanner;

public class BaekJoon1991 {
    static int[][] tree;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        tree = new int[N][2];
        //처음에 7 스캔하고 나면 빈 줄이 남아서 한번 더 스캔해야한다.
        sc.nextLine();
        for(int i=0;i<N;i++){
            String[] tmp = sc.nextLine().split(" ");
            int index = tmp[0].charAt(0)-'A';
            char left = tmp[1].charAt(0);
            char right = tmp[2].charAt(0);
            if(left=='.'){
                tree[index][0]=-1;
            }
            else{
                tree[index][0]=left-'A';
            }
            if(right=='.'){
                tree[index][1]=-1;
            }
            else{
                tree[index][1]=right-'A';
            }
        }
        preOrder(0);
        System.out.println();
        inOrder(0);
        System.out.println();
        postOrder(0);
        System.out.println();
    }

    private static void preOrder(int i) {
        if(i==-1) return;
        System.out.print((char)(i+'A'));
        preOrder(tree[i][0]);
        preOrder(tree[i][1]);
    }

    private static void inOrder(int i) {
        if(i==-1) return;
        inOrder(tree[i][0]);
        System.out.print((char)(i+'A'));
        inOrder(tree[i][1]);
    }

    private static void postOrder(int i) {
        if(i==-1) return;
        postOrder(tree[i][0]);
        postOrder(tree[i][1]);
        System.out.print((char)(i+'A'));
    }
}
