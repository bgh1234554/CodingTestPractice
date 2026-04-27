package Programmers.lv1;

import java.util.HashSet;
import java.util.Set;

public class Parallel {
    public static void main(String[] args) {
        System.out.println(new Parallel().solution(new int[][]{{1,4},{9,2},{3,8},{11,6}}));
    }
    //가능한 모든 점 조합에 대해서 중복 기울기가 있는지 검사하는 로직
    //미묘하지만 서로 다른 두 쌍에 대해 존재하는지를 파악해야 하기 때문에,
    //이렇게 하면 1,2번째 점과 1,3번째 점이 동일한 기울기를 가져도 1을 리턴해 틀릴 수 있다.
    public int wrongSolution(int[][] dots) {
        int answer = 0;
        boolean isXZero = false;
        boolean isYZero = false;
        Set<Double> results = new HashSet<>();
        for(int i=0;i<4;i++){
            for(int j=i+1;j<4;j++){
                double xdiff = dots[i][0]-dots[j][0];
                double ydiff = dots[i][1]-dots[j][1];
                if(xdiff==0){
                    if(isXZero){
                        return 1;
                    }
                    isXZero=true;
                }
                else if(ydiff==0){
                    if(isYZero){
                        return 1;
                    }
                    isYZero=true;
                }
                else if(!results.add(ydiff/xdiff)){
                    return 1;
                }
            }
        }
        return 0;
    }

    public int solution(int[][] dots) {
        // 네 점을 두 쌍으로 나누는 경우의 수만 확인
        // 가능한 경우의 수가 3개밖에 없으니까...
        if (isParallel(dots[0], dots[1], dots[2], dots[3])) return 1;
        if (isParallel(dots[0], dots[2], dots[1], dots[3])) return 1;
        if (isParallel(dots[0], dots[3], dots[1], dots[2])) return 1;
        return 0;
    }

    private boolean isParallel(int[] a, int[] b, int[] c, int[] d) {
        // 분모가 0인 경우(수직선) 따로 처리
        if (a[0] == b[0] && c[0] == d[0]) return true; // 둘다 분모가 0이면 평행하니까 true
        if (a[0] == b[0] || c[0] == d[0]) return false; // 이러면 한쪽만 y축에 평행하니까 false
        //나머지 경우에 대해선 기울기 계산
        double slope1 = (double)(a[1] - b[1]) / (a[0] - b[0]);
        double slope2 = (double)(c[1] - d[1]) / (c[0] - d[0]);
        return slope1 == slope2;
    }
}
/*
점 네 개의 좌표를 담은 이차원 배열  dots가 다음과 같이 매개변수로 주어집니다.

    [[x1, y1], [x2, y2], [x3, y3], [x4, y4]]

주어진 네 개의 점을 두 개씩 이었을 때, 두 직선이 평행이 되는 경우가 있으면 1을
없으면 0을 return 하도록 solution 함수를 완성해보세요.

제한사항

    dots의 길이 = 4
    dots의 원소는 [x, y] 형태이며 x, y는 정수입니다.
        0 ≤ x, y ≤ 100
    서로 다른 두개 이상의 점이 겹치는 경우는 없습니다.
    두 직선이 겹치는 경우(일치하는 경우)에도 1을 return 해주세요.
    임의의 두 점을 이은 직선이 x축 또는 y축과 평행한 경우는 주어지지 않습니다.

 */