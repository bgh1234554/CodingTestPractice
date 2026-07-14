package Programmers.lv1;

import java.util.Arrays;

/*
문제 설명

두 수를 입력받아 두 수의 최대공약수와 최소공배수를 반환하는 함수, solution을 완성해 보세요.
배열의 맨 앞에 최대공약수, 그다음 최소공배수를 넣어 반환하면 됩니다.
예를 들어 두 수 3, 12의 최대공약수는 3, 최소공배수는 12이므로 solution(3, 12)는 [3, 12]를 반환해야 합니다.

제한 사항

    두 수는 1이상 1000000이하의 자연수입니다.

 */
public class 최대공약수와최소공배수 {
    public static void main(String[] args) {
        int n = 3;
        int m = 12;
        System.out.println(Arrays.toString(solution(n, m)));
    }

    //일반적인 수학 공식으로 푸는 방법
    public static int[] solution(int n, int m) {
        int[] answer = new int[2];
        if(n>m){
            int tmp = n;
            n = m;
            m = tmp;
        }
        for(int i=1;i<=n;i++){
            if(n%i==0 && m%i==0){
                answer[0] = i;
                answer[1] = n*m/i; //n도 i를 인수로 갖고, m도 i를 인수로 가지니까, 그냥 곱하고 i로 나누면 되니까.
            }
        }
//        answer[0]= m%n==0 ? n : answer[0];
//        answer[1]= m%n==0 ? m : answer[1];
        return answer;
    }

    /*
    유클리드 호제법으로 푸는 방법

    위의 방법은 i를 1부터 N까지 탐색해, 시간복잡도가 O(N)이다.

    "두 수 n, m의 최대공약수는, m을 n으로 나눈 나머지 r과 n의 최대공약수와 같다"

    이 후 최소공배수는 아까 쓴 방법처럼 두 수를 곱해 최대공약수로 나누면 된다.
     */
    public static int[] solution2(int n, int m) {
        int gcd = getGCD(n, m);
        int lcm = n * m / gcd;
        return new int[]{gcd, lcm};
    }
    public static int getGCD(int a, int b){
        //매 바퀴마다 (a, b) 쌍을 (b, a%b)로 바꾸기. 이후 재귀적으로 반복.
        //이 쌍은 GCD 값을 그대로 유지한 채 숫자만 작아지는 변환이라는 것을 보장함.
        while (b != 0) {
            int tmp = b;
            b = a % b;   // 반복할 때마다 "더 큰 수를 작은 수로 나눈 나머지"로 문제를 축소
            a = tmp;     // a<b로 들어와도 이 한 바퀴에서 자동으로 스왑되어 교정됨
        }
        return a; // 이 시점 a가 최대공약수
    }
}
