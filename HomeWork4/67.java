public class Solution {
    public String addBinary(String a, String b) {
        int nStrLen1 = a.length();
		int nStrLen2 = b.length();
		
		int nStrLenRes = (nStrLen1 > nStrLen2)?nStrLen1:nStrLen2;
		
//		int[] nTempArray = new int[nStrLenRes];
		String strResult = new String();

		int i = 0;
		int j = 0;
		int k = nStrLenRes-1;
		int nFlag = 0;
		int nTemp = 0;
		
		for(i=nStrLen1-1,j=nStrLen2-1; i>=0; i--){
			if(j < 0)
				break;
			
			nTemp = a.charAt(i) - '0' + b.charAt(j) - '0' + nFlag;
			if(nTemp < 2){
				strResult = nTemp + strResult;
				nFlag = 0;
			}
			else{
				strResult = nTemp - 2 + strResult;
				nFlag = 1;
			}
			k--;
			j--;
		}
		
		if(j < 0)
			for(;i>=0; i--){
				nTemp = a.charAt(i) - '0' + nFlag;
				if(nTemp < 2){
					strResult = nTemp + strResult;
					nFlag = 0;
				}
				else{
					strResult = nTemp - 2 + strResult;
					nFlag = 1;
				}
				k--;
			}
		
		if(i < 0)
			for(;j>=0; j--){
				nTemp = b.charAt(j) - '0' + nFlag;
				if(nTemp < 2){
					strResult = nTemp + strResult;
					nFlag = 0;
				}
				else{
					strResult = nTemp - 2 + strResult;
					nFlag = 1;
				}
				k--;
			}
//		nTempArray[k] = nFlag;
		
		if(nFlag == 1)
			strResult = "1" + strResult;
		
		return strResult;  
    }
}