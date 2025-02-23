package DoIt.Chapter11_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon14003 {
    static int N, maxLength;
    //maxLength - B 배열 인덱스 업데이트 용 변수. 최대 길이 확인하는 용도
    static int[] B = new int[1000001];
    static int[] A = new int[1000001];
    static int[] D = new int[1000001];
    static int[] ans = new int[1000001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int index;
        B[++maxLength] = A[1];
        D[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (B[maxLength] < A[i]) { //가장 마지막 수열보다 현재 수열이 큰 경우
                B[++maxLength] = A[i];
                D[i] = maxLength;
            } else {
                index = binarysearch(1, maxLength, A[i]);
                //B배열에서 data[i]보다 처음으로 크거나 같아지는 원소의 index 찾기
                B[index] = A[i];
                D[i] = index;
            }
        }
        System.out.println(maxLength); //가장 긴 증가하는 부분 수열 길이 출력
        index = maxLength;
        int x = B[maxLength] + 1;
        for (int i = N; i >= 1; i--) { //뒤에서 부터 탐색하면서 정답 수열 저장하기
            if (D[i] == index && A[i] < x) {
                ans[index] = A[i];
                x = A[i];
                index--;
            }
        }
        for (int i = 1; i <= maxLength; i++)
            System.out.print(ans[i] + " ");
    }
    // 현재 수열이 들어 갈 수 있는 위치를 빠르게 찾아주기 위한 바이너리 서치 구현
    static int binarysearch(int l, int r, int now) {
        int mid;
        while (l < r) {
            mid = (l + r) / 2;
            if (B[mid] < now)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
    public static void solvedWithList(String[] args) throws IOException {
        // 입력을 받기 위한 BufferedReader 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 줄에서 정수 n을 읽어옴
        int n = Integer.parseInt(br.readLine());
        // n 크기의 배열 arr와 dp 배열 생성 (인덱스를 1부터 사용하기 위해 n+1 크기로 생성)
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        // LIS를 저장할 리스트 생성 - 위의 B배열 대용
        List<Integer> lis = new ArrayList<>();

        // 두 번째 줄에서 n개의 정수를 읽어와 arr 배열에 저장 (인덱스 1부터 저장)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int length = 0; // LIS의 길이를 저장할 변수
        for (int i = 1; i <= n; i++) {
            // lis 리스트에서 arr[i]의 위치를 이진 탐색
            int pos = Collections.binarySearch(lis, arr[i]);
            // 값이 없으면 삽입 포인트를 계산
            if (pos < 0) pos = -(pos + 1);

            // 삽입 포인트가 리스트의 크기 이상이면 리스트에 추가
            if (pos >= lis.size()) {
                lis.add(arr[i]);
                length++;
            } else {
                // 그렇지 않으면 해당 위치의 값을 arr[i]로 갱신
                lis.set(pos, arr[i]);
            }

            // dp 배열에 현재 위치의 LIS 길이 저장
            //위의 메인 메서드와 다른 방식으로 인덱스 차이 보정
            //다른건 다 1부터 시작하는데, list만 0부터 저장하니까.
            //이 코드는 Collections.binarySearch 메서드에서 반환된 값을 pos + 1로 보정하여 dp 배열에 저장한다.
            //이를 통해 인덱스 차이를 보정하고, main 메서드와 동일한 결과를 출력할 수 있다
            //list의 인덱스에 1을 더해야 길이 값이 완성되니까.
            dp[i] = pos + 1;
        }

        // LIS를 저장할 배열 생성
        int[] result = new int[length];
        for (int i = n; i >= 1; i--) {
            // dp 배열을 역순으로 탐색하여 LIS 복원
            if (dp[i] == length) {
                //길이가 인덱스가 되면 된다.
                //뒤에서부터 저장.
                result[--length] = arr[i];
            }
        }

        // 결과를 출력하기 위한 StringBuilder 객체 생성
        StringBuilder sb = new StringBuilder();
        sb.append(result.length).append("\n");
        for (int num : result) {
            sb.append(num).append(" ");
        }
        // 최종 결과 출력
        System.out.println(sb);
    }
    /*
    여기서는 base-case 처리 안하는 이유
    1. 초기화 필요 없음
        Collections.binarySearch 메서드를 사용하여 arr[i]의 위치를 찾고,
        그 위치에 값을 삽입하거나 갱신하는 과정에서 리스트가 자동으로 초기화됩니다.
        따라서 별도의 초기화 단계가 필요하지 않습니다.
    2. 동적 배열 관리
        lis 리스트는 동적으로 크기가 조절되며, 필요한 경우 자동으로 값을 추가하거나 갱신합니다.
        이는 Collections.binarySearch와 List.add 및 List.set 메서드의 조합으로 이루어집니다.
    3. 기본 케이스 처리
        기본 케이스는 for 루프의 첫 번째 반복에서 자동으로 처리됩니다.
        첫 번째 요소는 항상 lis 리스트에 추가되며, 이후 요소들은 이 리스트를 기반으로 처리됩니다.
     */
    //static int[] ans = new int[1000001]; 정답 배열 대신 StringBuilder 사용
    public static void failed(String[] args) throws IOException {
        BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(brr.readLine());
        StringTokenizer st = new StringTokenizer(brr.readLine());
        //계산 편하게 하려고 1부터 저장
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int index;
        //Base Case
        B[++maxLength] = A[1];
        D[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (B[maxLength] < A[i]) { //가장 마지막 수열보다 현재 수열이 큰 경우
                B[++maxLength] = A[i];
                D[i] = maxLength;
            } else {
                index = binarysearch(1, maxLength, A[i]);
                // B배열에서 data[i]보다 처음으로 크거나 같아지는 원소의 index 찾기
                /*
                index = Arrays.binarySearch(B, 1, maxLength + 1, A[i]);
                if (index < 0) index = -(index + 1); // 삽입 포인트 계산
                 */
                B[index] = A[i];
                D[i] = index;
            }
        }
        System.out.println(maxLength); //가장 긴 증가하는 부분 수열 길이 출력
        index = maxLength;
        StringBuilder sb = new StringBuilder();
        int x = B[maxLength] + 1; //가장 길게 증가하는 배열의 최댓값 계산하기 위한 변수
        for (int i = N; i >= 1; i--) { //뒤에서 부터 탐색하면서 정답 수열 저장하기
            if (D[i] == index && A[i] < x) {
                //ans[index] = A[i];
                sb.insert(0, A[i]+" ");
                x = A[i];
                index--;
            }
        }
//        for (int i = 1; i <= maxLength; i++)
//            System.out.print(ans[i] + " ");
        sb.reverse();
        System.out.println(sb);
        /*
         StringBuilder를 사용하면 문자열을 한 번에 출력할 수 있어 성능이 더 좋을 수 있지만,
         특정 상황에서는 StringBuilder의 insert 메서드가 많은 메모리 복사 작업을 유발하여
         성능 저하를 일으킬 수 있습니다.
         특히, insert(0, ...)를 반복적으로 호출하면 성능이 저하될 수 있습니다.
         */
    }
}
/*
일단 시간복잡도를 생각하지 않고 풀이해보자.

점화식은 쉽게 세울 수 있다.
D[i] = i번째 수를 "포함한" 수열 중 가장 길게 증가하는 수열의 길이

수열의 길이가 작다면, 다음과 같이 풀이를 작성할 수 있다.
D[i]=max(D[i],D[j]+1); where j<i && arr[j]<arr[i]
i이전의 DP 배열을 탐색해 인덱스 i보다 작은 원소를 갖는 인덱스 j의 DP 배열이 갖는 최댓값에다가 1을 더하면 된다.

문제는 이 문제에선 배열의 길이가 최대 10^9이기 때문에 for문을 통해 일일이 탐색하는 방법을 사용할 수 없다는 것이다.
그렇기 때문에 새롭게 D 배열을 업데이트할 때마다 j탐색이 필요 없이 미리미리 저장을 해놓도록,
따로 B배열을 만든다. B 배열은 특정 길이의 증가하는 부분 수열의 마지막 값을 저장하는 배열이다. 최솟값이 아니다.

매번 탐색 할 때, 현재까지 발견된 가장 긴 배열의 길이를 저장한 다음, 그 길이를 인덱스로 하는 B 배열의
원소의 크기와 값을 비교한다.

1. 발견된 가장 긴 배열의 길이를 인덱스로 하는 B 배열의 값보다, 현재 탐색하는 배열의 값이 크면,
새로운 긴 배열이 발견됐다는 것이니까 길이 변수에 1을 더해주고, B 배열에 그 길이를 인덱스로 해서 원소를 저장한다.
2. 그렇지 않을 경우에는
맞는 인덱스를 찾아야 한다. 시간 복잡도를 위해 이진 탐색을 이용해야 한다.
뭘 찾아야 하나? D[j]를 찾아야 한다
-> j값을 찾아야 한다. i보다 작은 원소들 중 최댓값을 찾아 현재 탐색하고 있는 원소가 "어느 자리에 들어가야 할지"
탐색 한 뒤 DP 배열을 업데이트 한다.
예를 들어 B[2]가 5고, 탐색하는 원소가 6이면 B[3]=6으로 업데이트 한다.

이와 동시에 DP 배열도 업데이트 한다. 현재까지 발견된 가장 긴 배열의 길이는 따로 변수로 저장하고 있으니까,
탐색이 종료되면 자연스럽게 가장 긴 증가하는 수열의 길이가 된다.

이후 가장 긴 수열을 출력하는 방법
배열을 거꾸로 탐색하면서 DP 배열의 값과 현재 우리가 필요한 값을 비교해 처음 만났을 때 출력한다.
LCS에서 LCS문자열을 출력하는 원리와 비슷하다. 말로 설명하는 것보다 코드로 보는 것이 쉽다.


이진탐색 시간 복잡도 관련
solvedWithList 함수가 더 빨리 실행되는 이유는 다음과 같습니다:
내장된 Collections.binarySearch 사용: solvedWithList 함수는 내장된
Collections.binarySearch 메서드를 사용하여 이진 탐색을 수행합니다.
이 메서드는 최적화된 라이브러리 함수로,
직접 구현한 binarysearch 메서드보다 더 빠르고 효율적으로 동작할 수 있습니다.


참고 (이진탐색 메서드 구현이 필요 없는 이유)
Arrays.binarySearch(arr, fromIndex, toIndex, key);
배열의 `Arrays.binarySearch`, 리스트의 `Collections.binarySearch` 메서드는
배열에 값이 있으면 해당 값의 인덱스를 반환하고, 값이 없으면 삽입 포인트의 음수 값을 반환합니다.
삽입 포인트는 배열에서 해당 값이 들어갈 위치입니다.

따라서 값이 없을 때 반환되는 값은 `-(삽입 포인트 + 1)`입니다.

예를 들어, 배열 `[1, 3, 5, 7]`에서 `binarySearch`를 사용하여 `4`를 찾으면,
`4`는 `3`과 `5` 사이에 들어가야 하므로 삽입 포인트는 `2`입니다. 이 경우 반환 값은 `-(2 + 1) = -3`이 됩니다.

이를 통해 값이 배열에 없을 때도 적절한 위치를 찾을 수 있습니다.
 */