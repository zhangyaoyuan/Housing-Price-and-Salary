public class Solution {
    public String reverseWords(String s) {
        String strResult = new String();
		String strTemp = s.trim();
		strTemp = strTemp.replaceAll(" +", " ");
		
		String[] strWords = strTemp.split(" ");
		
		int nWordsCount = strWords.length;
		if(nWordsCount == 0)
			return strResult;

		int j = nWordsCount - 1;
		
		while(j > 0){
			strResult = strResult + strWords[j] + " ";
			j--;
		}
		strResult += strWords[0];
		
		return strResult;
    }
}