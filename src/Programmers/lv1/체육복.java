package Programmers.lv1;

import java.util.Arrays;

/*
문제 설명

점심시간에 도둑이 들어, 일부 학생이 체육복을 도난당했습니다.
다행히 여벌 체육복이 있는 학생이 이들에게 체육복을 빌려주려 합니다.
학생들의 번호는 체격 순으로 매겨져 있어, 바로 앞번호의 학생이나 바로 뒷번호의 학생에게만 체육복을 빌려줄 수 있습니다.
예를 들어, 4번 학생은 3번 학생이나 5번 학생에게만 체육복을 빌려줄 수 있습니다.
체육복이 없으면 수업을 들을 수 없기 때문에 체육복을 적절히 빌려 최대한 많은 학생이 체육수업을 들어야 합니다.

전체 학생의 수 n, 체육복을 도난당한 학생들의 번호가 담긴 배열 lost,
여벌의 체육복을 가져온 학생들의 번호가 담긴 배열 reserve가 매개변수로 주어질 때,
체육수업을 들을 수 있는 학생의 최댓값을 return 하도록 solution 함수를 작성해주세요.

제한사항

전체 학생의 수는 2명 이상 30명 이하입니다.
체육복을 도난당한 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌의 체육복을 가져온 학생의 수는 1명 이상 n명 이하이고 중복되는 번호는 없습니다.
여벌 체육복이 있는 학생만 다른 학생에게 체육복을 빌려줄 수 있습니다.
여벌 체육복을 가져온 학생이 체육복을 도난당했을 수 있습니다.
이때 이 학생은 체육복을 하나만 도난당했다고 가정하며, 남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.

 */
public class 체육복 {
    public static void main(String[] args) {
        int n = 5;
        int[] lost = {2, 4};
        int[] reserve = {1, 3, 5};
        System.out.println(solution(n, lost, reserve)); // 5
    }

    //첫 시도 - 73.3/100
    //이때 이 학생은 체육복을 하나만 도난당했다고 가정하며,
    //남은 체육복이 하나이기에 다른 학생에게는 체육복을 빌려줄 수 없습니다.
    //위 조건에 대한 구현을 어떻게 해야할지 모르겠다.
    public static int solutionAttempt(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        Arrays.sort(lost);
        Arrays.sort(reserve);
        //잃어버린 학생 중 치수가 제일 작은 학생 부터
        //lost[idx]에 있는 옷이 대여가 가능한지 파악하기.
        //idx에 있는게 너무 작아서 못입은 걸수도 있으니까,
        int idx = 0;
        for (int i = 0; i < lost.length; i++) {
            while (reserve[idx] - lost[i] < -1) idx++; //너무 작은 옷들 다 패스
            if (idx >= reserve.length) break;
            //옷이 맞는지 확인
            if (Math.abs(reserve[idx] - lost[i]) <= 1) {
                answer++;
                idx++;
            }
            if (idx >= reserve.length) break;
        }
        return answer;
    }

    /*
    기존에 풀었던 방식의 문제점
    -> 두 배열의 중복 원소를 찾는 방식을 구현하려면 탐색은 Arrays.binarySearch(arr,8) 이런식으로 가능하지만,
       중복 원소 제거 로직이 복잡해진다.
    -> 새로운 구현 로직이 필요하다.

    새로운 방법 - 학생 번호를 index로 하는 count 배열을 만들기.
    -> count 배열로 만들면 중복 찾기도 쉽고 편한데, 가끔씩 까먹는 것 같다.
    -> N의 범위가 정해져 있다면 위와 같은 방법으로 풀면 풀이 시간을 줄일 수 있다.
     */
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        int[] cloth = new int[n+2];
        Arrays.fill(cloth,1,n+1,1); //모두 기본 1벌씩 옷 가지고 있음.
        for(int l:lost)cloth[l]--; //없는 애들 한개씩 없애고,
        for(int r:reserve)cloth[r]++; //여분 있는 애들 하나씩 더하기

        //순회 탐색하면서 0인 애들 앞 뒤로 2 이상인 애들이 있는지
        //결정 방식은 greedy. 앞을 선택하든 뒤를 선택하든, 순간의 선택이 전체 결과에 영향을 미치지 않기 때문.
        //
        //(추가로 알아낸 사실. 배열 정렬이나, if문에서 조건 식이 ==2인 이유)
        //여벌의 체육복을 가져온 학생에 중복되는 번호가 없으므로, 최대 한 사람 당 가질 수 있는 옷은 2벌.
        for(int i=1;i<=n;i++){
            if(cloth[i]==0){ //옷이 없으면
                //양 옆 번호 아이가 2벌을 갖고 있는 지 확인한다.
                if(cloth[i-1]==2){
                    cloth[i-1]--;
                    cloth[i]++;
                }
                else if(cloth[i+1]==2){
                    cloth[i+1]--;
                    cloth[i]++;
                }
            }
            if(cloth[i]>0) answer++;
        }
        return answer;
    }
}
/*
Arrays.fill이 뭔지
java.util.Arrays에 있는 정적 메서드고, 배열의 특정 구간을 지정한 값으로 한 번에 채워주는 함수.

// 전체를 채우는 버전
int[] arr = new int[5];
Arrays.fill(arr, 7);
// arr = [7, 7, 7, 7, 7]

// 구간을 지정하는 버전: fill(배열, fromIndex(포함), toIndex(제외), 값)
int[] arr2 = new int[5];
Arrays.fill(arr2, 1, 4, 9);
// arr2 = [0, 9, 9, 9, 0]  (인덱스 1,2,3만 채워짐, 4는 제외)

 */