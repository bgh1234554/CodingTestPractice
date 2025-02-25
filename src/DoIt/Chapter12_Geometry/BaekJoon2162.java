package DoIt.Chapter12_Geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2162 {
    static int[] parent; //부모 노드 저장 (union-find)
    static int[][] lines; //선분에 대한 정보. 각 좌표를 저장한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        lines = new int[N+1][4]; //좌표가 4개니까.
        //parent 배열 -1로 초기화 (이전과 다른 부분)
        for(int i=1;i<=N;i++){
            parent[i]=-1;
        }
        //저장할 때마다 이전에 저장된 선분과 비교해서 겹치면 통합함.
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            lines[i][0]=Integer.parseInt(st.nextToken());
            lines[i][1]=Integer.parseInt(st.nextToken());
            lines[i][2]=Integer.parseInt(st.nextToken());
            lines[i][3]=Integer.parseInt(st.nextToken());
            //이전에 저장된 선분과 교차되는지 일일이 비교
            //교차되면 union 실행
            for(int j=1;j<i;j++){
                if(isCross(lines[i][0],lines[i][1],lines[i][2],lines[i][3],
                        lines[j][0],lines[j][1],lines[j][2],lines[j][3])){
                    union(i,j);
                }
            }
        }
        //union이 끝났으니, parent 배열을 탐색하면서, 총 선분 그룹의 개수와
        //가장 큰 그룹을 출력
        int group=0; int amount=0;
        for(int i=1;i<=N;i++){
            //음수란건 그룹의 총 선분의 개수가 절댓값이라는 거니까.
            if(parent[i]<0){
                group++;
                amount = Math.min(amount,parent[i]);
            }
        }
        System.out.println(group);
        System.out.println(-amount); //양수로 바꾸기
    }
    //변형된 union-find 알고리즘
    //parent 배열에 음수가 저장되어있다면, 절댓값이 그룹에 있는 선분의 수
    //양수가 저장되어 있다면 기존에 알고 있던 부모 노드의 인덱스
    private static int find(int x){
        if(parent[x]<0){
            return x;
        }
        return parent[x]=find(parent[x]);
    }

    private static void union(int i,int j){
        i = find(i);
        j = find(j);
        //두 부모노드가 같으면 더 이상 진행할 필요가 없음.
        if(i==j) return;
        //다르면, 합치는데, 한쪽에는 선분의 개수에 대한 정보를
        //다른 한 쪽에는 새로운 부모노드의 인덱스를 저장.
        parent[i]+=parent[j];
        //음수가 저장됨. (j가 부모노드이거나, 아직 -1이거나 둘 중 하나임.) find 함수의 원리 때문에.
        parent[j]=i; //양수가 저장됨.
    }

    private static int ccw(long x1, long y1, long x2, long y2, long x3, long y3){
        //실제 값 대신 방향만 리턴
        long tmp = x1*y2+x2*y3+x3*y1-x1*y3-x2*y1-x3*y2;
        if(tmp<0) return -1;
        else if(tmp>0) return 1;
        else return 0;
    }

    private static boolean isCross(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        //교차하거나, 일직선에서 겹치는지 확인
        int abc = ccw(x1,y1,x2,y2,x3,y3);
        int abd = ccw(x1,y1,x2,y2,x4,y4);
        int cda = ccw(x3,y3,x4,y4,x1,y1);
        int cdb = ccw(x3,y3,x4,y4,x2,y2);
        //만약 일직선에 있다면 겹치는지를 리턴해야함.
        if(abc*abd==0&&cda*cdb==0) return isOverlap(x1,y1,x2,y2,x3,y3,x4,y4);
            //일직선에는 없으면 두 곱이 모두 음수인지 true/false 리턴하기.
        else return abc * abd <= 0 && cda * cdb <= 0;
    }

    private static boolean isOverlap(long x1, long y1, long x2, long y2, long x3, long y3, long x4, long y4) {
        //한 선분의 min값이, 다른 선분의 max값 보다 클 경우 겹치지 않는다.
        //직접 그림을 그려보고 식을 적어보면 더 편하다.
        return Math.min(x1,x2)<=Math.max(x3,x4)&&Math.min(x3,x4)<=Math.max(x1,x2)
                &&Math.min(y1,y2)<=Math.max(y3,y4)&&Math.min(y3,y4)<=Math.max(y1,y2);
    }
}
/*
이전에 배웠던 union-find를 변형한 방식을 사용하는데,
union-find에 음수를 사용한다던가, parent 배열에 넣는 값이 기존에 단순히 부모 노드를 넣는 방식과는
세부적으로 차이가 있어 이해하기 힘들었다.
 */