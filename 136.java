public class Solution {
    public int singleNumber(int[] nums) {
        //a^b = b^a, a^a =0,0^a=a,所以将所有元素全部进行异或操作
        //，最后就剩下单独的元素。
        int elem = 0;
        for(int i=0; i<nums.length; i++){
            elem = elem ^ nums[i];
        }
        return elem;
    }
}