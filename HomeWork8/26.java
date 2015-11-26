public class Solution {
    public int removeDuplicates(int[] nums) {
        int j = 0;
		int i;
		if(nums.length <= 1) return nums.length;
		for(i=0; i<nums.length && j<nums.length; i++)
		{
			j++;
			while(j < nums.length && nums[i] == nums[j])
				j++;

			if(j < nums.length)
			{
				nums[i+1] = nums[j];	
			}
		}
		return i;
    }
}