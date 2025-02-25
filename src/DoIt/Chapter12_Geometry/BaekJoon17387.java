package DoIt.Chapter12_Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon17387 {
    static long[] x = new long[4];
    static long[] y = new long[4];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0;i<4;i+=2){
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i]=Long.parseLong(st.nextToken());
            y[i]=Long.parseLong(st.nextToken());
            x[i+1]=Long.parseLong(st.nextToken());
            y[i+1]=Long.parseLong(st.nextToken());
        }
        boolean cross = isCross();
        if(cross) System.out.println(1);
        else System.out.println(0);
    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3){
        //실제 값 대신 방향만 리턴
        long tmp = x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2;
        if(tmp<0) return -1;
        else if(tmp>0) return 1;
        else return 0;
    }

    private static boolean isCross() {
        //교차하거나, 일직선에서 겹치는지 확인
        int abc = ccw(x[0],y[0],x[1],y[1],x[2],y[2]);
        int abd = ccw(x[0],y[0],x[1],y[1],x[3],y[3]);
        int cda = ccw(x[2],y[2],x[3],y[3],x[0],y[0]);
        int cdb = ccw(x[2],y[2],x[3],y[3],x[1],y[1]);
        //만약 일직선에 있다면 겹치는지를 리턴해야함.
        if(abc*abd==0&&cda*cdb==0) return isOverlap();
        //일직선에는 없으면 두 곱이 모두 음수인지 true/false 리턴하기.
        else return abc * abd <= 0 && cda * cdb <= 0;
    }

    private static boolean isOverlap() {
        //한 선분의 min값이, 다른 선분의 max값 보다 클 경우 겹치지 않는다.
        //직접 그림을 그려보고 식을 적어보면 더 편하다.
        return Math.min(x[0],x[1])<=Math.max(x[2],x[3])&&Math.min(x[2],x[3])<=Math.max(x[0],x[1])
                &&Math.min(y[0],y[1])<=Math.max(y[2],y[3])&&Math.min(y[2],y[3])<=Math.max(y[0],y[1]);
    }
}
