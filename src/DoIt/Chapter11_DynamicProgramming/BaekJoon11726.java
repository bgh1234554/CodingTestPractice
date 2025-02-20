package DoIt.Chapter11_DynamicProgramming;

import java.util.Scanner;

public class BaekJoon11726 {
    public static void main(String[] args) {
        long[] result = new long[1001];
        result[1]=1; result[2]=2;
        for(int i=3;i<1001;i++){
            result[i]=(result[i-2]+result[i-1])%10007;
        }
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(result[N]);
    }
}
/*
점화식은 간단하나, 왜 그런 점화식이 나오게 되었는지 도출하는 과정이 매우 복잡하다.
2*(N-2)와 2*(N-1)을 이용하는 것까지는 쉽게 떠올릴 수 있어도,
그 이후에 어떻게 점화식을 만들어야 하는지,
해답을 보고도 왜 이렇게 점화식이 유도되는지에 대해 고민을 많이 했다.

그리고 이거 옥스퍼드 대학교 수학과 면접 문제였던 걸로 기억하는데 거기서 가져온건가?
 */