package DoIt.Chapter04.Chapter04_6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10989_CountingSort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] count = new int[10001];

        for (int i = 0; i < N; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < count.length; i++) {
            while (count[i] > 0) {
                sb.append(i).append('\n');
                count[i]--;
            }
        }
        System.out.print(sb);
    }
}
/*
계수 정렬로 풀어본 문제
계수 정렬 설명 - https://velog.io/@wndudrla1011/interview-algorithm-sort-counting
문제 - https://www.acmicpc.net/problem/10989

말 그대로 들어오는 데이터의 최댓값을 알 때, 인덱스를 값으로 하는 계수 배열을 만든다.
그러면 count 배열에 개수 정보가 저장된다.
그리고 1부터 10000까지 돌면서 count[i]가 0이 될 때까지 출력한다.

다만 배열의 최댓값을 모를 경우나 값의 편차가 클 경우, 원본 배열에 비해 count배열이 비해 메모리를 많이 차지할 수 있다.
공간 복잡도가 O(n)이지만, 시간 복잡도가 O(n)이기 때문에 백준의 문제가 짖궂어서 Arrays.sort()함수를 써도
시간 초과가 될 경우에 계수정렬 (Counting Sort)를 사용하면 좋다.
 */