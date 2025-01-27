package AlgoMap_io.ArraysAndString;

import java.util.Arrays;
/*
Given an array of integers citations
where citations[i] is the number of citations a researcher received for their ith paper, return the researcher's h-index.

According to the definition of h-index on Wikipedia:
The h-index is defined as the maximum value of h
such that the given researcher has published at least h papers that have each been cited at least h times.
 */
public class Leetcode274 {
    public static void main(String[] args) {
        System.out.println(hIndex(new int[]{3,0,6,1,5}));
    }
    public static int hIndex(int[] citations) {
        Arrays.sort(citations); //정렬 후
        if(citations[citations.length-1]==0){
            return 0;//최댓값 0이면 0 리턴
        }
        else if(citations.length==1){
            return 1; //아닌데 길이 1이면 그냥 1리턴.
        }
        //최댓값보다 하나 더 (0개 인용된 것도 있으니까)
        int[] count = new int[citations[citations.length-1]+1];
        int[] sum = new int[citations[citations.length-1]+1];
        for(int i=0;i<citations.length;i++){
            count[citations[i]]++;
        }
        sum[0]=count[0];
        for(int i=1;i<count.length;i++){
            sum[i]=sum[i-1]+count[i];
        }
        //핵심부분
        int h;
        for(h=sum.length-1;h>=0;h--){
            //count[h]>0일 필요가 없다. 0,0,2이면 count[h]=0이어도 h값은 1이니까.
            //h개 인용된 논문수+h개 초과로 인용된 논문 수가 h보다 많아야 한다.
            //최댓값을 구해야 하니 역으로 탐색.
            if((count[h]+citations.length-sum[h])>=h){
                break;
            }
        }
        return h;
    }
    public static int hIndex2(int[] citations) {
        int n = citations.length;
        int[] paperCounts = new int[n + 1]; //h의 가능한 법위는 0부터 citations의 최댓값이니까.
        for (int c : citations) {
            paperCounts[Math.min(n, c)]++; //어차피 h값의 최대는 citations.length니까 넘어가면 그냥 거기에 저장.
            //0개 인용, 1개 인용, ..., citations.length개 이상 인용
        }
        int h = n;
        int papers = paperCounts[n]; //h개 이상 인용된 논문 수 찾기
        //위의 sum배열 역할을 이 papers 변수 하나로 처리할 수 있다.
        while (papers < h) {
            h--; //h값을 하나 줄이고
            papers += paperCounts[h]; //h--개만큼 인용된 논문수를 더해 정보 업데이트.
        }
        return h;
    }
}
/*
계수 정렬을 단순히 0~배열의 원소 최댓값의 크기로 하는 것이 아니라, 다르게 해서 풀 수 있다는 사실을 알았다.
sum배열을 만들지 않고도 그 배열의 원소 역할을 해줄 수 있는 포인터를 만들어 해결하는 방법도 있다는 사실을 알았다.
속도도 3ms vs 0ms이다.
h의 최댓값이 'h개 이상 인용'만 보고 citations의 원소의 최댓값이 h의 최댓값이겠거니 했는데,
'h개 논문 이상이'이기 때문에 발행한 논문 수가 h의 최댓값이다. (6회 인용됐는데 논문이 5개면 h의 최댓값은 5니까.)
 */