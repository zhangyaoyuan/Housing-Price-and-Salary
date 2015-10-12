public class Solution {
    public int reverse(int x) {
        int nResult = 0;
        
        while(x != 0){
        	nResult = nResult * 10 + x % 10;
        	x = x / 10;
        	if((x != 0) && 
        			(Integer.MAX_VALUE / 10 < nResult || Integer.MIN_VALUE / 10 > nResult) )
        		return 0;
        }
        
		return nResult;
    }
}