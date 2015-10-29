public class Solution {
    public int myAtoi(String str) {
        str = str.trim();
	        int nStrLen = str.length();
			int nFlag = 1;
			int i=0;
			long nResultNum = 0;
			
			if(nStrLen == 0)
				return 0;
			
			
			if(str.charAt(i) == '-')
			{
				nFlag = -1;
				i++;
				if(!Character.isDigit(str.charAt(i)))
				    return 0;
			}else if(str.charAt(i) == '+'){
			    nFlag = 1;
			    i++;
			    if(!Character.isDigit(str.charAt(i)))
				    return 0;
			}else if(!Character.isDigit(str.charAt(i)))
			    return 0;
			
			for(; i<nStrLen; i++)
			{
//				char c = str.charAt(i);
				if(Character.isDigit(str.charAt(i)))
				{
					int nCurNum = str.charAt(i) - '0';
					nResultNum = nResultNum * 10 + nCurNum;
					
					if(nResultNum >= (long)Integer.MAX_VALUE + 1)
						break;
				}else
					break;
			} // end of for
			
			if(nResultNum*nFlag >= Integer.MAX_VALUE)
				return Integer.MAX_VALUE;
			
			if(nResultNum*nFlag <= Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
			
			return (int)nResultNum*nFlag;
    }
}