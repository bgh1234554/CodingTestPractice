import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Remember {
    public static void main(String[] args) throws Exception {
        //1. Scanner은 느리다. BufferedReader로 입력을 받는다.
        //이후 Integer.parseInt, StringTokenizer을 이용한다.
        //2. System.out.println도 느리다. BufferedWriter가 좋다.
        //Stringbuilder로 다 모아준 다음 println을 사용해도 된다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            //공백을 기준으로 자르는 StringTokenizer을 사용할 수 있다.
            int m = Integer.parseInt(st.nextToken());
            for(int j=0;j<m;j++){
                int data=Integer.parseInt(st.nextToken());
                sb.append(data).append('\n');
                bw.write(String.valueOf(data)); //int를 그대로 출력할 수가 없다.
                bw.newLine();
            }
        }
        System.out.println(sb);
        bw.flush(); bw.close(); br.close();
        //마지막에 close 해주는게 좋다. bw는 flush도 하고.
        //사실 프로그래머스에서 치면 입력부분은 신경 안써도 된다.

        //3. 자바에서 배열에 연산할때 쓸 수 있는 Arrays 클래스
        int arr[] = {10, 8, 11, 2, 3, 0};
        Arrays.sort(arr);
        //내림차순으로 정렬하고 싶으면 배열을 클래스의 배열로 만들어줘야 한다.
        //아님 그냥 -1 곱하고 나중에 출력할때 -1 곱해서 보여주면 되는거 아닌가?
        //이진 탐색
        int index = Arrays.binarySearch(arr,2);
        Arrays.copyOf(arr,arr.length); //배열 복사
        Arrays.copyOfRange(arr,1,4); //from부터 to-1까지 복사.
        //int 배열을 Integer 배열로. .stream(arr).boxed()까진 동일
        Integer[] tmp = Arrays.stream(arr).boxed()/*.distinct()*/.toArray(Integer[]::new);
        //distinct는 중복 제거시 사용할 수 있다.
        //stream으로 만들어주고, box를 씌워 wrapper 클래스로 만들고,
        //Integer생성자를 활용한다.
        List<Integer> list = Arrays.asList(tmp);
        int[] tmp2 = Arrays.copyOfRange(arr,0,3); //0,1,2번째 인덱스 복사
        String str = "abc";
        //String을 문자의 배열로.
        char[] c = str.toCharArray();
        //String의 길이는 length(), 배열의 길이는 length, List의 길이는 size

        //문자열 뒤집기
        String origin = "Reverse";
        String rev = new StringBuilder(origin).reverse().toString();

        //ArrayList에는 클래스만 담을 수 있다.
        ArrayList<Integer> arrlist = new ArrayList<>(Arrays.asList(12,3,4));
        //안됨 ArrayList<Integer> arrlist2 = List.of(1,2,34,5);
        //int[]를 List<Integer>로. 컬렉션으로 바꿔야하니 collect를 쓴다로 기억하자.
        List<Integer> intList = Arrays.stream(arr).boxed()/*.distinct()*/.collect(Collectors.toList());
        //List<int[]>를 int[][]로 변환
        List<int[]> merged = new ArrayList<>();
        int[][] mergedarr = merged.toArray(new int[merged.size()][]);
        //List<Integer>를 int[]로 변환
        int[] arrlist2 = arrlist.stream().mapToInt(Integer::new).toArray();

        //List 깊은 복사
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>(); list2.addAll(list1);
        Collections.sort(list1); //리스트 정렬
        Collections.sort(list1, Collections.reverseOrder()); //내림차순 정렬
        //Collections에 sort, max, min있음. Collectors에는 toList()

        //Comparator 인터페이스 구현. C언어의 qsort 함수 구현과 비슷한 것 같다.
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,2); map.put(4,5);
        Comparator<Map.Entry<Integer,Integer>> comparator = new Comparator<Map.Entry<Integer,Integer>>(){
            @Override
            public int compare(Map.Entry<Integer,Integer> o1, Map.Entry<Integer,Integer> o2) {
                return o1.getValue().compareTo(o2.getValue()); //compareTo 대소관계 비교.
                //비교대상보다 크면 +, 작으면 -를 리턴한다.
            }
        };
        Map.Entry<Integer,Integer> maxEntry = Collections.max(map.entrySet(),comparator);
        Map.Entry<Integer,Integer> minEntry = Collections.min(map.entrySet(),comparator);
        //Arrays.sort(T[], Comparator<? super T> compare); compare 함수를 두번째 인자로 넣어야 한다.
        //compare 함수 - 어떤 방식으로 정렬할 건지 기준을 제시해주는 함수.
        //Integer.compare(1,2); -> 어떤 객체 안에 있는 int 필드값을 비교할때 사용할 수 있다.
        /*
        Node[] nodes = new Node[5];
        Arrays.sort(nodes, (o1,o2)->Integer.compare(o1.second,o2.second));
         */
        ArrayList<Integer> intList2 = new ArrayList<>();
        intList2.add(2); intList2.add(5);
        intList2.set(0,2*intList.get(0)); //값 변경할때 이렇게 하면 됨.

        //밑의 줄로 옮겨가기: Ctrl Shift Enter
        //변수 이름 한번에 바꾸기: Shift F6
        //변수 이름 만들어주기: Ctrl Alt V
        //리팩터링 - Ctrl Alt Shift T
        //https://velog.io/@iui9686/Intellij#-%EC%A3%BC%EC%9A%94-%EB%8B%A8%EC%B6%95%ED%82%A4
        //sout 하면 바로 println 출력된다.
    }
}
