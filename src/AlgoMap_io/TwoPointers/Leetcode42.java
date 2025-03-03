package AlgoMap_io.TwoPointers;

public class Leetcode42 {
    public static void main(String[] args) {
        System.out.println(trapWithArray(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        //leftMax = [0,1,1,2,2,2,2,3,3,3,3,3]; rightMax = [3,3,3,3,3,3,3,3,2,2,2,1];
        System.out.println(trapAlgomapIo(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        //maxLeft = [0,0,1,1,2,2,2,2,3,3,3,3]; maxRight = [3,3,3,3,3,3,3,2,2,2,1,0];
    }
    //폐기 - 오른쪽이 무조건 더 높다고 가정해서 안됨...
//    public static int trap(int[] height) {
//        int left = 0; int result = 0;
//        while(left<height.length-2){
//            if(height[left+1]>=height[left]){left++; continue;}
//            boolean foundRight = false;
//            for(int right=left+1;right<height.length;right++){
//                if(height[right]>=height[left]){
//                    foundRight = true;
//                    int waterheight = Math.min(height[left],height[right]);
//                    for(int k=left+1;k<right;k++){
//                        result+=waterheight-height[k];
//                    }
//                    left=right;
//                }
//            }
//            if(!foundRight){
//                left++;
//            }
//        }
//        return result;
//    }
    //각 지점마다 찰 수 있는 물의 높이는 이전 폐기된 함수에서도 구할줄 알았다.
    //문제는 그 높이를 어떻게 알아내느냐인데...
    //각 지점마다 왼쪽과 오른쪽에서의 최대 벽 높이를 계산하면 된다. 근데 미리 계산하면 더 좋지 않을까?
    //시간복잡도 - O(n) 공간복잡도 - O(n)
    public static int trapWithArray(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            //만약 leftMax[i-1]과 height[i-1]을 비교하는 방식으로 leftMax를 짰다면,
            //마지막 res에 물 높이를 계산시 0 이상인지 확인해야하나, 미리 height[i]와 비교하게 된다면
            //(만약 음수가 나온다는건 자신의 높이가 좌우 최대 높이보다 더 크다는 거니까, 자신을 포함해서 계산하게 변경)
            //최소 0 이상이 보장된다.
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
    //leftMax와 rightMax 배열을 투 포인터로 대체할 수 있다.
    /*
    좌우 투 포인터를 지정하고,
    각 지점의 최대 높이를 leftMax와 rightMax 변수에 별도로 저장한다.

    우리는 좌우 높이의 최솟값에 바닥 높이를 빼서, 물의 높이를 계산한다.
    예를 들어, leftMax가 rightMax보다 작다고 하자.
    그러면 l을 오른쪽으로 한칸 옮겼을 시, leftMax가 더 작으므로, 그 인덱스의
    rightMax의 실제 값이 무엇이든지 상관없이 leftMax - height[l]로 물 높이가 결정된다.
    이는 rightMax가 더 작을 때, r에 대해서도 동일하게 성립한다.

    이를 l<r 인 동안 계속해서 반복해주면 정답이 나온다.

    위의 leftMax, rightMax 배열 업데이트 방식과 같이,
    여기서도 Math.max에서 이미 자기 자신의 높이와 한번 더 최대높이를 미리 비교하기 때문에,
    res에 더할 때, 0 이상인지 확인할 필요가 없다.
     */
    //시간복잡도 - O(n), 공간복잡도 - O(1)
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
    /*
    Algomap.io의 Gregg Hogg의 방법.
    배열을 사용했으며, 0인지 아닌지를 검사하기 때문에,
    Neetcode의 코드보다 더 직관적이다.
     */
    public static int trapAlgomapIo(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        //leftMax, rightMax에 해당하는 변수
        int lWall = 0, rWall = 0;

        for (int i = 0; i < n; i++) {
            int j = (n-1) - i;
            //배열에 먼저 저장하기 때문에, 현재 높이를 제외한 최대 높이가 업데이트된다.
            //maxLeft[0]과 maxRight[n-1]을 먼저 base-case로 업데이트하고, for문을 각각 돌렸으면,
            //바로바로 이해할 수 있었을텐데...
            maxLeft[i] = lWall;
            maxRight[j] = rWall;
            //이후 현재 높이를 포함해 업데이트
            lWall = Math.max(lWall, height[i]);
            rWall = Math.max(rWall, height[j]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            //둘 중 더 작은 높이에서 바닥 높이를 뺴는데,
            //0보단 커야 하니까 Math.max 사용
            int pot = Math.min(maxLeft[i], maxRight[i]);
            sum += Math.max(0, pot - height[i]);
        }

        return sum;
    }
}
//참고 해설 - https://neetcode.io/solutions/trapping-rain-water