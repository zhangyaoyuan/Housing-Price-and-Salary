public class Solution {
    public List<List<Integer>> twoSum(int nums[],int m, int n, int target)
	{
		int nStartPoint = m;
		int nLastPoint = n;
		List<List<Integer>> listRst = new LinkedList<List<Integer>>();
		
		for(; nStartPoint<nLastPoint; nStartPoint++)
		{
			while(nums[nStartPoint]+nums[nLastPoint]>target && nStartPoint<nLastPoint-1)
				nLastPoint--;
			
			if(nums[nStartPoint]+nums[nLastPoint] == target)
			{
				List<Integer> listInt = new LinkedList<Integer>();
				listInt.add(nums[nStartPoint]);
				listInt.add(nums[nLastPoint]);
				if(!listRst.contains(listInt))
					listRst.add(listInt);
			}
		}
		return listRst;
	}
	
	public List<List<Integer>> fourSum(int[] nums, int target) {
		int nStartPoint = 0;
		int nLastPoint = nums.length-1;

		Arrays.sort(nums);
		List<List<Integer>> listRst = new LinkedList<List<Integer>>();
		
		for(; nStartPoint<nums.length-3; nStartPoint++)
		{	
			nLastPoint = nums.length-1;
			for(; nStartPoint<nLastPoint; nLastPoint--)
			{
				List<List<Integer>> listInt = 
						twoSum(nums, nStartPoint+1, nLastPoint-1, target-nums[nStartPoint]-nums[nLastPoint]);
				for(int j=0; j<listInt.size(); j++)
				{
					List<Integer> listTmp = new LinkedList<Integer>();
					listTmp.add(nums[nStartPoint]);
					listTmp.add(listInt.get(j).get(0));
					listTmp.add(listInt.get(j).get(1));
					listTmp.add(nums[nLastPoint]);
					if(!listRst.contains(listTmp))
						listRst.add(listTmp);
				}
			}
			
		}
		
		return listRst;
    }
}