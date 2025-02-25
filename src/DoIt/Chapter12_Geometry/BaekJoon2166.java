package DoIt.Chapter12_Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2166 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] x = new long[N+1];
        long[] y = new long[N+1];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }
        x[N]=x[0]; y[N]=y[0]; //한바퀴 돌아야 하니까
        double result = 0;
        for(int i=0;i<N;i++){
            result+=x[i]*y[i+1]-x[i+1]*y[i];
        }
        //format 맞춰서 만드는거 알아두기
        //Python의 print.format과 비슷한 것 같다.
        String ans = String.format("%.1f",Math.abs(result)/2.0);
        System.out.println(ans);
    }
}
/*
신발끈 공식인 CCW의 값은 세 점을 이루는 두 벡터의 외적의 값이다. 이는 평행사변형의 넓이를 나타낸다.
다만 부호가 달린 값이라 연산 순서에 따라 부호가 바뀐다.

옛날에 초등학교때 오각형 이상의 넓이를 구하려면 삼각형이나 사각형으로 쪼개라는 말을 들어봤을 것이다.
그것의 확장판이라고 보면 된다.

원점과 다각형위 두 점을 상대로 외적을 해 모두 더한 다음 절댓값을 씌워준다.
어차피 마지막에 절댓값을 씌우기 때문에 방향을 반대로 해서 더한다고 해도 괜찮다.

삼각형의 합을 구해야하니 CCW를 2로 나눈 값을 구해야 한다.
꼭짓점의 갯수만큼 CCW를 구해서 다 더한 다음에 절댓값을 취하고 2로 나눈다.

int가 아닌 long형을 사용해야 하는 이유
result 변수에 결과를 더할 때, x[i]*y[i+1]의 최댓값이 10^10인데, 이는 int형의 범위를 넘어서기 때문에
두 항을 곱한 임시 결과에서 overflow가 발생해 값이 틀릴 수가 있다.
 */
