public class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] numsTemp = new int[m];
        numsTemp = nums1.clone();
        
		while((i < m) && (j < n)){
			if(numsTemp[i] <= nums2[j])
			{
				nums1[k] = numsTemp[i];
				i++;
				k++;
				continue;
			}

			if(numsTemp[i] > nums2[j])
			{
				nums1[k] = nums2[j];
				j++;
				k++;
				continue;
			}
		} // end of while
		
		while(i < m)
		{
			nums1[k] = numsTemp[i];
			i++;
			k++;
		}
			
		while(j < n)
		{
			nums1[k] = nums2[j];
			k++;
			j++;
		}
    }
}