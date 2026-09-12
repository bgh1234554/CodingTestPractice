package Programmers.lv2;

/*
문제 설명

H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다.
어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고
나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때,
이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

제한사항

    과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
    논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
 */
public class HIndex {
    public static void main(String[] args) {
        int[] citations = {3, 0, 6, 1, 5};
        System.out.println(solution(citations));
    }

    public static int solution(int[] citations) {
        int[] docs = new int[citations.length + 1]; //index번 인용된 citations 수 (어차피 문서 수가 h index 최댓값이니까)
        for(int citation : citations){
            docs[Math.min(citation, citations.length)]++;
        }
        int h = citations.length; //가능한 최댓값부터 역으로 탐색
        int count = docs[h]; //h번 이상 인용된 논문 수
        while(h > 0 && count < h){ //역으로 탐색해서
            h--; //만족 안하면 1씩 내려서 다시 검사하기
            count += docs[h];
        }
        return h;
    }
}
//리트코드 시절에 했던건데 다시 풀어보려니까 잘 안된다. 역시 복습이 중요한 것 같다.