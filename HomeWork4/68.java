public class Solution {
    public int lengthOfLastWord(String s) {
        int nStrLen = s.length();
		if(nStrLen == 0)
			return 0;
			
        String[] strArray = s.split(" ");
        if(strArray.length == 0)
            return 0;
            
        int nCountResult = 0;
        String strLastWord = strArray[strArray.length-1];
        nCountResult = strLastWord.length();
        
        return nCountResult;
    }
}