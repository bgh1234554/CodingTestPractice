package AlgoMap_io.ArraysAndString;

public class Leetcode238 {
    public static void main(String[] args) {
        productExceptSelf(new int[]{-1,1,0,-3,3});
    }
    public static int[] productExceptSelf(int[] nums) {
        int[] output = new int[nums.length];
        int product = 1;
        for(int i=0;i<nums.length;i++){
            output[i]=product; //한칸 옆에 저장해야하니까 먼저 저장
            product*=nums[i];
        }
        product=1;
        for(int i=nums.length-1;i>=0;i--){
            output[i]*=product;
            product*=nums[i];
        }
        return output;
    }
    /*
    처음에는 오른쪽 곱과 왼쪽 곱을 서로 곱하면 답이 된다는 Discussions의 풀이를 보고,
    투 포인터 문제인가? 하고 생각했는데, Categories의 Prefix Sum을 보고 여기에 구간합을 넣는다고?
    라는 생각이 먼저 들었다.
    굉장히 어려운 구간합 문제였다.
    구간합에서 평행이동의 개념은 처음 생각해야했다.
    어차피 전체 곱에서 나누기를 하는 방식은 원소에 0이 들어가기 때문에 쓸 수 없다.
    보톹 구간 곱을 구할때는,
    product[i]=nums[i]*product[i-1]을 취하는데,
    일부러 nums[i-1]을 곱해 한칸씩 결과를 옮길 수 있다.
    밑에 있는 pre배열과 suff배열을 사용하는 방식을 먼저 이해한다음,
    위의 코드를 구현하려고 하면 이해가 될 것이다.
    pre,suff,ans배열을 따로따로 만들지 않고 ans배열에 바로 곱하는 방식으로 구현하면
    위와 같이 공간복잡도 정답배열 제외 O(1), 시간 복잡도 O(N)의 방식으로 구현할 수 있다.
     */
    public static int[] productExceptSelfSpaceON(int[] nums) {
        int n = nums.length;
        int pre[] = new int[n];
        int suff[] = new int[n];
        pre[0] = 1; //왼쪽부터 차례로 곱하기
        suff[n - 1] = 1; //오른쪽부터 차례로 옮겨서 곱하기
        //한칸씩 옆으로 옮겨서 저장.
        for(int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }
        for(int i = n - 2; i >= 0; i--) {
            suff[i] = suff[i + 1] * nums[i + 1];
        }

        int ans[] = new int[n];
        //이후 정답배열에 곱해서 저장하면 된다.
        for(int i = 0; i < n; i++) {
            ans[i] = pre[i] * suff[i];
        }
        return ans;
    }
}
