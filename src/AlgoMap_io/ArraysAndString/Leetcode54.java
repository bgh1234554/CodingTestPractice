package AlgoMap_io.ArraysAndString;

import java.util.ArrayList;
import java.util.List;
/*
Given an m x n matrix, return all elements of the matrix in spiral order.
 */
public class Leetcode54 {
    public static void main(String[] args) {
        int[][] matrix = {
                {3},
                {2}
        };
        System.out.println(spiralOrder(matrix));
    }
    public static List<Integer> spiralOrder(int[][] matrix) {
        int row=0; int col=0;
        int n=matrix.length; int m=matrix[0].length;
        List<Integer> ans=new ArrayList<>();
        while(ans.size()<m*n){
            for(int i=col;i<=m-1-col;i++){
                ans.add(matrix[row][i]);
            }
            for(int i=row+1;i<=n-2-row;i++){
                ans.add(matrix[i][m-1-col]);
            }
            for(int i=m-1-col;i>=col;i--){
                ans.add(matrix[n-1-row][i]);
            }
            for(int i=n-2-row;i>=row+1;i--){
                ans.add(matrix[i][col]);
            }
            row++; col++;
        }
        return ans.subList(0,m*n);
    }
    public static List<Integer> spiralOrderCopilot(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return ans;
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    ans.add(matrix[bottom][i]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    ans.add(matrix[i][left]);
                }
                left++;
            }
        }
        return ans;
    }
}
/*
처음에 보면 이게 뭔지 싶으나, 차분히 생각해보면 된다. 어떤 방식으로 위, 오른쪽, 아래, 왼쪽을 탐색할 건지 생각하고
각 탐색에 대한 일반식을 만들어보려고 노력해보면 된다.
이전의 rotate matrix도 그렇고 오히려 행렬 관련을 더 잘맞히는 것 같다. 힌트를 하나도 안보고 혼자 고민해서 풀어서
기분이 좋다.
변수를 더 많이 선언하지만, 보기엔 더 간결하고 이쁜 코파일럿이 만든 방식도 있으니 참고하면 좋다.
상한선, 하한선, 우한선, 좌한선을 각각 설정해 한번 탐색할때마다 경계를 변경해주는 방식이다.
 */
/*
<비교>
나의 풀이
    장점:
        간결한 반복문 구조로 각 방향을 순회.
        코드가 직관적이며, 각 방향의 순회를 명확히 구분.
    단점:
        경계 조건을 직접 계산하여 코드가 다소 복잡.
        경계 조건을 잘못 설정할 경우 무한 루프에 빠질 가능성.
        코드의 가독성이 떨어질 수 있음.
Copilot의 풀이
    장점:
        경계(top, bottom, left, right)를 명확히 설정하여 코드의 가독성이 높음.
        각 방향의 순회를 별도의 조건문으로 처리하여 코드가 더 명확.
        경계 조건을 쉽게 이해할 수 있어 유지보수가 용이.
    단점:
        변수 선언이 많아 코드가 다소 길어질 수 있음.
        초기 경계 조건 설정이 필요하여 약간의 오버헤드가 있을 수 있음.
결론
    두 풀이 모두 나선형 순회를 잘 구현하고 있다.
    사용자의 풀이는 간결하지만 경계 조건을 직접 계산하는 부분이 복잡할 수 있다.
    반면, Copilot의 풀이는 경계 조건을 명확히 설정하여 가독성과 유지보수 측면에서 더 유리하다.
 */