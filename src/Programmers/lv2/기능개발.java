package Programmers.lv2;

import java.util.ArrayList;
import java.util.List;

/*
문제 설명

프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고,
이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와
각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때
각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

제한 사항

    작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
    작업 진도는 100 미만의 자연수입니다.
    작업 속도는 100 이하의 자연수입니다.
    배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다.
    예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.

*/
public class 기능개발 {
    public static void main(String[] args) {

    }

    //직관적으로 푼 나의 풀이 - 하루하루씩 시뮬레이션
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int sum = 0;
        while(sum!=progresses.length){
            //하루치 작업
            for(int i=0;i< progresses.length;i++){
                progresses[i] += speeds[i];
            }
            //배포해야 하는지 확인하기
            int count = 0;
            for(int j=sum;j<progresses.length;j++){
                //배포해야할 게 있으면 추가 후 계속 검사
                if(progresses[j]>=100){
                    sum++; count++;
                }
                //안되면 어차피 뒤에거 배포를 못하니까 바로 탈출
                else{
                    break;
                }
            }
            if(count!=0) answer.add(count);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    //날짜 계산만 빠르게 해서 정답에 추가하는 방식
    public int[] solutionByChatGPT(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        // 첫 번째 기능이 완료되는 날짜
        int deployDay = (100 - progresses[0] + speeds[0] - 1) / speeds[0];
        int count = 1;

        for (int i = 1; i < progresses.length; i++) {
            // 현재 기능이 완료되는 날짜
            int completeDay =
                    (100 - progresses[i] + speeds[i] - 1) / speeds[i];

            // 앞 기능의 배포일의 이전까지 완료된다면 함께 배포
            if (completeDay <= deployDay) {
                count++;
            } else {
                // 안된다면 앞 배포 그룹을 정답에 추가 후
                answer.add(count);

                // 현재 기능부터 새로운 배포 그룹 시작
                deployDay = completeDay;
                count = 1;
            }
        }

        // 마지막 배포 그룹 추가
        answer.add(count);

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
