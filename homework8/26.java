
public class Solution {
    public int removeDuplicates(int[] nums) {
      
        int length=nums.length;
		int i;
		int count=0;
		for(i=0;i<length;i++)
		{
			int j=i+1;
			while(j<length&&nums[i]==nums[j])
			{
				j++;
				
			}
			
			int k=i+1;
			
			while(j!=i+1&&k<j&&j<length)
			{
				nums[k]=nums[j];
				k++;
			}
			
		}
		
		
		
		for(i=0;i<length;i++)
		{
			if(i<length-1&& nums[i]!=nums[i+1])count++;
			else break;
			
		}
		
		return count+1;
        
    }
}