package Programmers.lv0;
/*
머쓱이는 태어난 지 6개월 된 조카를 돌보고 있습니다.
조카는 아직 "aya", "ye", "woo", "ma" 네 가지 발음을 최대 한 번씩 사용해 조합한(이어 붙인) 발음밖에 하지 못합니다.
문자열 배열 babbling이 매개변수로 주어질 때,
머쓱이의 조카가 발음할 수 있는 단어의 개수를 return하도록 solution 함수를 완성해주세요.
 */
public class Babbling {
    public static void main(String[] args) {
        String[] str1 = {"aya", "yee", "u", "maa", "wyeoo"};
        String[] str2 = {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};
        Babbling sol = new Babbling();
        System.out.println(sol.solution(str1));
        System.out.println(sol.solution(str2));
    }
    public int solution(String[] babbling) {
        String[] cases = {"aya", "ye", "woo", "ma"};
        int answer = 0;
        for(String str : babbling){
            if(canPronounce(str,cases)){
                answer++;
            }
        }
        return answer;
    }
    public boolean canPronounce(String target,String[] cases){
        int index = 0; //지금 어느 부분부터 찾는지
        while(true){
            int pos=101; //문자열 위치 (최대길이 100이니까 101로 초기화)
            int min=16; // target의 최대 길이가 15이므로 16으로 초기화
            for(int i=0;i<4;i++){
                int possible = target.indexOf(cases[i],index);
                if(possible!=-1&&possible<=pos){
                    pos=target.indexOf(cases[i],index);
                    min=i; //가장 가까이 가능한 옹알이의 위치
                }
            }
            if(pos!=index){ // 이 부분이 제일 중요. IDE의 디버깅 기능이 없다면 몰랐을 것 같다.
                //처음에는 pos!=0으로 했는데,
                //그러면 다음 인덱스를 찾을 때, indexOf가 0번째 인덱스부터 고려했을 때의 인덱스를 반환한다는 것을 까먹었다.
                return false;
            }
            index = pos+cases[min].length();
            if(index==target.length()){
                return true;
            }
        }
    }
}
