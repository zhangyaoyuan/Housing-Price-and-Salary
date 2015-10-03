public class Solution {
    public void sortColors(int[] nums) {
        int N = nums.length;
        int temp=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N-1-i; j++){
                if(nums[j] > nums[j+1]){
                   temp = nums[j];
                   nums[j] = nums[j+1];
                   nums[j+1] = temp;
                }
            }
        }
    }
}