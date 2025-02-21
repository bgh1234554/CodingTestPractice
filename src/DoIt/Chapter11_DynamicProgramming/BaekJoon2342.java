package DoIt.Chapter11_DynamicProgramming;

import java.util.Scanner;

public class BaekJoon2342 {
    public static void main(String[] args) {
        //각 발의 움직임을 편하게 구하려고 만들어놓은 배열. i에서 j로 발이 이동했을 떄 드는 힘 정리.
        //0에서 다른 곳을 갈때 2가 들지만, 다른 곳에서 0으로 돌아갈 때도 2가 드는 것 같다.
        int[][] move = {{0,2,2,2,2},{2,1,3,4,3},{2,3,1,3,4},{2,4,3,1,3},{2,3,4,3,1}};
        int DP[][][] = new int[100001][5][5];
        //까먹었다. 최솟값을 확인해야 하니, 최댓값으로 초기화시킨다.
        for(int i=0;i<DP.length;i++){
            for(int j=0;j<5;j++){
                for(int k=0;k<5;k++){
                    DP[i][j][k]=400001;
                    //너무 큰 수로 초기화하면 오버플로우가 발생해 음수가 되어
                    //Math.min이 제대로 동작할 수 없다.
                }
            }
        }
        DP[0][0][0]=0;
        int length=1;
        Scanner sc = new Scanner(System.in);
        while(true){
            int step = sc.nextInt();
            if(step==0) break;
            //왼발이 step으로 움직였을 때
            for(int i=0;i<5;i++){
                //DP[length][step][i] -> 왼발이 step에 있을 때 오른발의 위치
                if(i==step) continue; //같은 발에 있으면 안되니까
                //어디서 step까지 왔는지?
                for(int j=0;j<5;j++){
                    DP[length][step][i]=Math.min(DP[length][step][i],DP[length-1][j][i]+move[j][step]);
                }
            }
            //오른발이 step으로 움직였을 때
            for(int i=0;i<5;i++){
                //DP[length][i][step] -> 오른발이 step에 있을 때 오른발의 위치
                if(i==step) continue; //같은 발에 있으면 안되니까
                //어디서 step까지 왔는지?
                for(int j=0;j<5;j++){
                    DP[length][i][step]=Math.min(DP[length][i][step],DP[length-1][i][j]+move[j][step]);
                }
            }
            length++;
        }
        length--; //1부터 시작해서 count랑 다르다
        int min = Integer.MAX_VALUE;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                min = Math.min(min,DP[length][i][j]); //이것땜에 위에서 초기화시킨것
            }
        }
        System.out.println(min);
    }
    public static void main2(String[] args) {
        int[][] move = {{0,2,2,2,2},{2,1,3,4,3},{2,3,1,3,4},{2,4,3,1,3},{2,3,4,3,1}};
        //각 단계 별 배열을 전부 저장할 필요가 없으니까, 이전 단계 배열과 현재 단계 배열의
        //2차원 배열 2개를 만들어서 해결할 수 있다. 나머지 로직은 동일하다.
        int[][] DP = new int[5][5];
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                DP[i][j] = 400001;
            }
        }
        DP[0][0] = 0;
        Scanner sc = new Scanner(System.in);
        int length = 0;
        while(true){
            int step = sc.nextInt();
            if(step == 0) break;
            int[][] newDP = new int[5][5];
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    newDP[i][j] = 400001;
                }
            }
            for(int i=0;i<5;i++){
                for(int j=0;j<5;j++){
                    if(step != j) newDP[step][j] = Math.min(newDP[step][j], DP[i][j] + move[i][step]);
                    if(step != i) newDP[i][step] = Math.min(newDP[i][step], DP[i][j] + move[j][step]);
                }
            }
            DP = newDP;
            length++;
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                min = Math.min(min, DP[i][j]);
            }
        }
        System.out.println(min);
    }
}
/*
아까 3차원 배열보단 쉽지만, 여전히 어렵다
점화식 자체는 경우의 수 노가다이지만,
구현이 더 어려운거 같다. move 배열 생성이나, 이중 for문을 통해 구현하는 아이디어를 얻기 등등...

두 메서드 모두 시간복잡도는 O(n)으로 동일합니다.
그러나 main2 메서드는 2차원 배열을 사용하여 메모리 사용량을 줄였기 때문에,
실제 실행 시간에서는 main2 메서드가 더 효율적일 수 있습니다.
 */