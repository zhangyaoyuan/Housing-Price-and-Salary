public class Solution {
    public boolean isHappy(int n) {
        boolean bIsHappy = true;
        int nTemp = n;
        int nResult = 0;
        Map map = new HashMap();
        while(nResult != 1 && nResult != n){
        	nResult = 0;
        	while(nTemp > 0){
            	nResult = nResult + (nTemp % 10)*(nTemp % 10);
            	nTemp = nTemp / 10;
            }
        	nTemp = nResult;
        	if(!map.containsKey(nTemp)){
        		map.put(nTemp, "");
        	}else
        		break;
        }
        
        if(nResult == 1)
        	bIsHappy = true;
        else
        	bIsHappy = false;
        
        return bIsHappy;
    }
}