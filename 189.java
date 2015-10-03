public class Solution {
    public void rotate(int[] nums, int k) {
        if(nums == null || nums.length == 0)return;
        int n = nums.length;
        k = k%n;
        if(k == 0)return;
	/*
	*先将数组整体倒置
	*再将前K个元素倒置
	*然后将后面的倒置
	*/	
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums,k,n-1);
    }
    public void reverse(int[] nums,int left,int right){
        int temp = 0;
        while(left < right){
            temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] =temp;
        }
    }
}