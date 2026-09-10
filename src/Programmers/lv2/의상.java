package Programmers.lv2;

import java.util.HashMap;

/*
어려움!!!

문제 설명

코니는 매일 다른 옷을 조합하여 입는것을 좋아합니다.

예를 들어 코니가 가진 옷이 아래와 같고, 오늘 코니가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면
다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야합니다.

종류 	이름
얼굴 	동그란 안경, 검정 선글라스
상의 	파란색 티셔츠
하의 	청바지
겉옷 	긴 코트

코니는 각 종류별로 최대 1가지 의상만 착용할 수 있습니다.
예를 들어 위 예시의 경우 동그란 안경과 검정 선글라스를 동시에 착용할 수는 없습니다.
착용한 의상의 일부가 겹치더라도, 다른 의상이 겹치지 않거나,
혹은 의상을 추가로 더 착용한 경우에는 서로 다른 방법으로 옷을 착용한 것으로 계산합니다.
코니는 하루에 최소 한 개의 의상은 입습니다.

코니가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때
서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.

 */
public class 의상 {
    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"yellow_hat","headgear"},{"blue_sunglasses","eyewear"},{"green_turban","headgear"}})); // 5
    }
    public static int solution(String[][] clothes) {
        HashMap<String,Integer> closet = new HashMap<>(); //옷 수만 알면 되니까 Integer로 하면 됨.
        String[] kinds = new String[clothes.length];
        int i = 0;
        for(String[] cloth : clothes){
            if(!closet.containsKey(cloth[1])) kinds[i++] = cloth[1];
            closet.put(cloth[1],closet.getOrDefault(cloth[1],0)+1);
        }
        int ans = 0;
//        for(int j = 0; j < i; j++){
//            ans += closet.get(kinds[j]);
//            int combination = closet.get(kinds[j]);
//            for(int k = j-1 ; k >= 0; k--){
//                combination *= closet.get(kinds[k]);
//            }
//            if(j>0) ans += combination;
//        }
        for (int j = 0; j < i; j++) {
            int count = closet.get(kinds[j]);
            int previousCombinations = ans;
            // 이번 종류의 옷만 입는 경우
            ans += count;
            // 기존의 모든 조합에 이번 종류의 옷을 추가하는 경우
            ans += previousCombinations * count;
        }
        return ans;
    }
    public static int solutionSimple(String[][] clothes) {
        HashMap<String, Integer> closet = new HashMap<>();
        // 기존 코드와 동일하게 종류별 옷의 개수를 저장한다.
        for (String[] cloth : clothes) {
            String kind = cloth[1];
            closet.put(kind,closet.getOrDefault(kind, 0) + 1);
        }
        int ans = 0;

        /*
         * 기존 코드에서는 옷 종류를 kinds 배열에 따로 저장한 후
         * closet.get(kinds[j])로 개수를 가져왔다.
         *
         * 하지만 필요한 것은 옷 종류의 이름이 아니라 종류별 옷의 개수이므로,
         * closet.values()를 사용해 개수만 바로 꺼낼 수 있다.
         *
         * 따라서 kinds 배열, i 변수, containsKey() 검사가 필요 없어졌다.
         */
        for (int count : closet.values()) {
            // 이번 종류를 처리하기 전에 만들어진 모든 조합의 수
            int previousCombinations = ans;
            // 이번 종류의 옷만 하나 입는 경우
            ans += count;
            /*
             * 기존에 만들어진 각각의 조합에
             * 이번 종류의 옷 count개 중 하나를 추가하는 경우
             */
            ans += previousCombinations * count;
        }
        return ans;
    }
    public static int solutionFailed(String[][] clothes) {
        HashMap<String,String[]> closet = new HashMap<>();
        String[] kinds = new String[clothes.length];
        int i = 0;
        for(String[] cloth : clothes){
            if(!closet.containsKey(cloth[1])) kinds[i++] = cloth[1];
            closet.put(cloth[1],clothes[0]);
        }
        int ans = 0;
        for(int j = 0; j < i; j++){
            ans += closet.get(kinds[j]).length;
            int combination = closet.get(kinds[j]).length;
            for(int k = j-1 ; k >= 0; k--){
                combination *= closet.get(kinds[k]).length;
            }
            if(j>0) ans += combination;
        }
        return ans;
    }
}
