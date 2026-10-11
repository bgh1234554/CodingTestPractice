package Programmers.lv2;
/*
문제 설명

조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)

예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.

만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
제한 사항

    name은 알파벳 대문자로만 이루어져 있습니다.
    name의 길이는 1 이상 20 이하입니다.
 */
public class 조이스틱 {
    public static void main(String[] args) {
        System.out.println(solution("JEROEN"));
        System.out.println(solution("JAZ"));
        System.out.println(solution("JAN"));
        System.out.println(solution("BBAA"));
    }

    //상하 이동과 좌우 이동을 따로. 상하는 지금처럼 for문으로 계산만 하면 되고
    //좌우는 커서가 걷는 경로 후보들의 좌우 이동 횟수 계산.
    public static int solution(String name){
        int answer = 0;

        //상하 이동 - 거꾸로 돌리는 것과 정방향으로 돌리는 것 비교 후 더하기
        for(int i=0;i<name.length();i++) {
            char cur = name.charAt(i);
            answer += Math.min(26 - (cur - 'A'), cur - 'A');
        }

        //좌우 이동
        // {A가 아닌 알파벳} {A 덩어리} {A가 아닌 알파벳 모음}
        //이라고 할때, A는 좌우 이동으로 갈 필요가 없다.
        //위치 0에서 시작해서 가는 방법은 오른쪽으로 쭉 가는 방법 (name.length()-1번 이동)
        // 위치 i 까지 오른쪽으로 간다음
        // 방향을 틀어서 반대쪽으로 오른쪽 A가 아닌 알파벳 모음의 제일 왼쪽 끝까지 커버하는 것이다.
        // 예를 들어, "BAABAAB"의 경우
        // i는 0,3,6이 될 수 있다.
        // i가 0이면 바로 왼쪽으로 가 오른쪽 A가 아닌 알파벳 모음의 제일 왼쪽 끝인 인덱스 3의 B까지 간다.
        // 그래서 매번 i를 for문으로 바꾸고, 오른쪽 A가 아닌 알파벳 모음의 제일 왼쪽 끝을 계산한 다음 next라 하자.
        // 0~i까지 갔다가 다시 돌아와서 next까지 가는 경우의 수,
        // 0에서 next쪽으로 갔다가 돌아와서 i까지 가는 경우의 수
        // 위 2개를 계산한 뒤, 정방향으로 가는 경우의 수와 비교한다.
        int horizontal = name.length()-1; //그냥 정방향으로 쭉 가는 경우
        for(int i=0;i<name.length();i++) {
            int next = i+1;
            while(next<name.length()&&name.charAt(next)=='A') next++;
            int rightFirst = 2*i + (name.length() - next);
            int leftFirst  = i + 2*(name.length() - next);
            horizontal = Math.min(horizontal, Math.min(rightFirst, leftFirst));        }
        answer += horizontal;
        return answer;
    }

    //81.5%까지 늘림 - 좌우 컨트롤의 그리디는 순간순간 가장 가까운 non-A를 찾아가는게 아님.
    public static int solution2(String name) {
        int answer = 0;
        int pos = 0;
        int left = 1; int right = name.length()-1; // 왼쪽/오른쪽부터 채워야 할 범위
        while(left<=right){
            //상하 컨트롤
            char cur = name.charAt(pos);
            answer += Math.min(26-Math.abs(cur-'A'),cur-'A');

            //좌우 컨트롤
            //다음에 가야할 곳 선정 (A스킵)
//            if(left==name.length()-1||right==0) break; //다 커버했으니까 break
            if(pos==left) left++;
            else if (pos==right) right--;
            while(left<name.length()&&name.charAt(left)=='A') left++;
            while(right>=0&&name.charAt(right)=='A') right--;
            //둘 중 어디가 가까운지 판별
            int leftMove = Math.min(Math.abs(pos-left), name.length()-Math.abs(pos-left));
            int rightMove = Math.min(Math.abs(pos-right), name.length()-Math.abs(pos-right));
            answer += Math.min(leftMove, rightMove);
            pos = leftMove<rightMove?left:right;
        }
        return answer;
    }

    //연속 A 고려 안함
    public static int solution1(String name) {
        int answer = 0;
        for(int i=0;i<name.length();i++){
            //상하 이동
            char cur = name.charAt(i);
            answer += Math.min(26-Math.abs(cur-'A'),cur-'A');
            //좌우 이동
            if(i!=name.length()-1 && name.charAt(i+1)=='A'){
                answer += Math.min(2, i+1);
                i++;
            }
            else if (i!=name.length()-1) answer++;
        }
        return answer;
    }
}
