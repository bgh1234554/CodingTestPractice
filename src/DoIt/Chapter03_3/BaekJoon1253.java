package DoIt.Chapter03_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for(int i=0;i<N;i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        for(int i=0;i<N;i++){
            int start=0, end=N-1, sum;
            while(start<end){
                if(start==i){
                    start++;
                    continue;
                }
                if(end==i){
                    end--;
                    continue;
                }
                //continue를 안넣으면 while루프의 조건문에 맞지 않는데 조건문 안의 명령을 수행할 수 있다.
                sum=arr[start]+arr[end];
                if(sum==arr[i]){
                    cnt++;
                    break;
                }
                else if(sum>arr[i]){
                    end--;
                }
                else{
                    start++;
                }
            }
        }
        System.out.println(cnt);
    }
}
/*
10
1 2 3 4 5 6 7 8 9 10
3
0 0 1
4
0 0 0 1
2
0 0
문제 부연 설명
리스트 A의 원소를 가리키는 서로 다른 인덱스 값 a, b, c에 대하여,
a번째 숫자를 b번째 숫자와 c번째 숫자의 합으로 나타낼 수 있다면 a번째 숫자는 좋은 숫자입니다.

예시)
리스트가 0, 0, 1로 이루어져있을 때, 이 리스트에는 좋은 숫자가 없습니다.
리스트가 0, 1, 1로 이루어져있을 때, 이 리스트의 좋은 숫자는 2번째 숫자, 3번째 숫자로, 총 2개가 존재합니다.
2번째 숫자 = 1번째 숫자 + 3번째 숫자
3번째 숫자 = 1번째 숫자 + 2번째 숫자
이기 때문입니다.

리스트가 0, 0, 0, 1로 이루어져있을 때, 이 리스트의 좋은 숫자는 1번째 숫자, 2번째 숫자, 3번째 숫자로, 총 3개가 존재합니다.
1번째 숫자 = 2번째 숫자 + 3번째 숫자
2번째 숫자 = 1번째 숫자 + 3번째 숫자
3번째 숫자 = 1번째 숫자 + 2번째 숫자
이기 때문입니다.

합을 일일이 구할 수 없기 때문에 투포인터를 사용해야 하는데,
이때 포인터가 자기 자신을 가리키지 않도록 whlie루프 처음에 if문으로 건너뛰도록 하였다.
(자기 자신은 안된다는 말도 분명하게 안 적혀있고, 인덱스가 다르면 값이 같아도 다른 수로 친다는 말을 처음에 못알아들었다.)
start와 end가 작동하는 방식은 1253번과 동일하다.
작은 수를 고정시켜놓고 구하면 인덱스를 벗어나는 예외가 발생하기 때문에, 큰 수를 고정시켜놓고 투 포인터를 실행한다.

for문의 i가 0부터 시작하는 이유는
3
0 0 0
일때 0번째와 1번째 수에 대해 좋은 수인지 검사하기 위해서이다.

- if문 2개에 continue;를 써야하는 이유.
2
0 0을 했을때
whlie루프의 첫번째 if문에서 start++를 수행하면
start=end=1이 되기 때문에 while루프를 만족하지 못하는데도
arr[start]+arr[end]=0이 되기 때문에 cnt가 올라간다.
if문 2개에 continue;를 넣으니까 해결됐습니다.

이거때문에 30분동안 이 문제만 붙잡고 있었다...
 */
