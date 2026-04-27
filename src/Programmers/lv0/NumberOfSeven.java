package Programmers.lv0;

import java.util.Arrays;
import java.util.stream.Collectors;
/*
머쓱이는 행운의 숫자 7을 가장 좋아합니다.
정수 배열 array가 매개변수로 주어질 때, 7이 총 몇 개 있는지 return 하도록 solution 함수를 완성해보세요.
 */
public class NumberOfSeven {
    public int solution(int[] array) {
        int answer = 0;
        for(int num:array){
            String str = Integer.toString(num);
            int result = countSubString(str,"7");
            answer+=result;
        }
        return answer;
    }
    public int countSubString(String target,String sub){
        int result = 0;
        int index = 0;
        while(true){
            int pos = target.indexOf(sub,index);
            if(pos==-1) break;
            result++;
            index=pos+sub.length();
        }
        return result;
    }
    /*
    특정 문자열 내에 문자열 substring이 존재하는지 검사하는 방법을 구현하는 문제.
    indexOf이라는 메서드를 오랜만에 쓸 수 있는 기회여서 좋았다.
     */

    //한자리수식 검사하는 방법 - 정석
    //숫자니까 사용할 수 있는 방법이다.
    public int solutionByDigit(int[] array) {
        int answer = 0;
        for(int a : array){
            while(a != 0){
                if(a % 10 == 7){
                    answer++;
                }
                a /= 10;
            }
        }
        return answer;
    }

    //덜 직관적이지만 자바 스트림을 사용하는 방법도 있다.
    public int solutionWithStream(int[] array) {
        return (int) Arrays.stream(
                        Arrays.stream(array)
                                .mapToObj(String::valueOf)
                                .collect(Collectors.joining())
                                .split("")
                )
                .filter(s -> s.equals("7"))
                .count();
    }
}
