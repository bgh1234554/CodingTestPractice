package Programmers.lv2;

import java.util.PriorityQueue;

/*
문제 설명

0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고,
이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때,
순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항

    numbers의 길이는 1 이상 100,000 이하입니다.
    numbers의 원소는 0 이상 1,000 이하입니다.
    정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.

 */
public class 가장큰수 {
    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};
        System.out.println(solution(numbers));
        numbers = new int[]{3, 30, 34, 5, 9};
        System.out.println(solution(numbers));
    }

    public static String solution(int[] numbers){
        //정답: 두 개를 이어 붙인 결과 자체를 비교 기준으로 삼기
        //compareTo -> 두 문자열의 사전적 순서를 비교하는 메서드
        //compare 관련 규칙 - "음수를 반환하면, 첫 번째 인자가 앞에 온다"
        //a=3,b=30을 비교할때 303과 330을 비교하면, 303이 사전적으로 앞에 오므로 3이 앞에 오게 정렬
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> (b + a).compareTo(a + b));
        for (int number : numbers) {
            pq.add(String.valueOf(number));
        }

        //numbers = [0, 0, 0]이면, 지금 로직대로면 결과가 "000"이 돼버리기 때문에 edge 케이스 처리
        if (pq.peek().equals("0")) return "0";

        //ans += pq.poll()처럼 String을 반복문 안에서 +=로 이어붙이면 매번 새 문자열 객체가 생성돼서 비효율적
        StringBuilder ans = new StringBuilder();
        while (!pq.isEmpty()) {
            ans.append(pq.poll());
        }
        return ans.toString();
    }

    //저 3을 30보다 앞에 오게 정렬하는 조건을 어떻게 작성해야 할지 모르겠다...
    public static String solutionFailed(int[] numbers) {
        PriorityQueue<String> pq = new PriorityQueue<>((a,b)->{
            if(a.charAt(0)==b.charAt(0)){
                return Integer.parseInt(b)-Integer.parseInt(a);
            }
            return b.charAt(0)-a.charAt(0);
        });
        for(int number : numbers){
            pq.add(String.valueOf(number));
        }
        String ans = "";
        while(!pq.isEmpty()){
            ans += pq.poll();
        }
        return ans;
    }

}
/*
**1) 커스텀 정렬을 설계하는 사고 순서 (창의력보다 이 순서가 중요)**

1. **"정렬 기준이 뭔지"부터 명확히 하기**:
    지금 문제는 "숫자 크기"가 정렬 기준이 아니라 **"이어붙였을 때 결과 문자열이 더 큰가"**가 기준이었어.
    "무엇을 기준으로 크다/작다를 정할 것인가"를 먼저 명확한 문장으로 적어보는 게 첫 단계야.
2. **작은 예시 2개만 놓고 손으로 직접 정답 순서를 정해보기**
    전체 배열이 아니라 딱 2개(`"3"`, `"30"`)만 놓고 "어느 게 앞에 와야 하지?"를 손으로 계산해봐.
    이 2개짜리 판단 로직을 코드로 옮기면 그게 바로 comparator야.
3. **그 판단을 "두 원소만의 함수"로 일반화하기**
    3번에서 나온 로직(`(a+b)`와 `(b+a)` 비교)이 임의의 두 원소 `a`, `b`에도 똑같이 적용되는지 확인.\
    Comparator는 결국 "임의의 두 원소를 주면 순서를 정해주는 함수"이기만 하면 돼.

**2) 자주 나오는 "창의적 comparator" 패턴 카탈로그**

이런 패턴들을 미리 알고 있으면, 새 문제를 만났을 때 "어? 이거 그 패턴이랑 비슷한데"라고 매칭할 수 있어.

| 패턴 | 예시 문제 | 핵심 아이디어 |
|---|---|---|
| 이어붙여서 비교 | 가장 큰 수 | `(a+b).compareTo(b+a)` — 두 원소를 합친 결과 자체를 비교 |
| 여러 기준을 순서대로 적용 | 실패율 (동점이면 스테이지 번호순) | 1차 기준이 같을 때만 2차 기준으로 |
| 정렬 대상과 기준을 분리 | 실패율(스테이지 번호 vs 실패율) | 값 배열과 기준 배열을 따로 두고 comparator 안에서 참조 |
| 길이/개수로 먼저 그룹, 그 안에서 값으로 | (예시: 문자열 길이순 정렬 후 사전순) | `thenComparing` 체이닝 |

**3) 다중 기준 정렬은 `thenComparing`으로 깔끔하게 체이닝 가능**

지금까지는 `if-else`로 직접 짰지만(실패율 문제), Java 8+ 스타일로 쓰면 훨씬 선언적이야:

```java
List<Rate> sorted = list.stream()
    .sorted(Comparator.comparingDouble((Rate r) -> r.rate).reversed()
        .thenComparingInt(r -> r.stage))
    .collect(Collectors.toList());
```

- `Comparator.comparing(키추출함수)` — 기준 하나를 함수로 뽑아서 정렬
- `.reversed()` — 내림차순으로 뒤집기
- `.thenComparing(...)` — 1차 기준이 같을 때 적용할 2차 기준

**4) 놓치기 쉬운 함정: Comparator는 "일관성(transitive)"이 있어야 해**

`a`가 `b`보다 크고, `b`가 `c`보다 크면, 반드시 `a`가 `c`보다 커야 해.
복잡한 커스텀 규칙을 짜다가 이 성질이 깨지면, Java가 정렬 도중
`"Comparison method violates its general contract!"`라는 런타임 에러를 던지기도 해'
(TimSort 알고리즘이 이 모순을 감지하면).
"이어붙여서 비교"(`a+b` vs `b+a`) 같은 패턴이 검증된 이유는,
이게 실제로 수학적으로 일관성(전이성)이 보장되는 규칙이기 때문이야
— 아무 규칙이나 떠오르는 대로 짜면 이 함정에 빠질 수 있어.

**정리**

"창의적인 조건"이라기보다,
**"두 원소만 놓고 판단 기준을 명확히 세운 뒤,
그 기준이 모든 쌍에 일관되게 적용되는지 확인하는" 절차**를 따르면 대부분의 커스텀 정렬 문제는 풀려.
이런 패턴들은 몇 번 접하면 "아 이거 그 패턴이네"하고 빨리 알아채게 될 거야
— 지금 두 번째(실패율)와 세 번째(가장 큰 수) 문제로 이미 두 가지 대표 패턴을 겪은 셈이니까.
 */