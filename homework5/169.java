public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if(n == 1)return nums[0];
        for(int i=0; i<n; i++){
            if(nums[i] == nums[i+n/2])return nums[i];
        }
        return -1;
    }
}