public class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.equals(""))
        	return true;
/*        
        int i = 0;
        int j = s.length() - 1;
*/        
        String str = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase(); //remove all c not in 
        int i = 0;
        int j = str.length() - 1;
        
        while(i < j){
/*        	while((i < j) && 
        			!(Character.isDigit(s.charAt(i)) || Character.isAlphabetic(s.charAt(i)) )){
        		i++;
        	}
        	while((i < j) && 
        			!(Character.isDigit(s.charAt(j)) || Character.isAlphabetic(s.charAt(j)) )){
        		j--;
        	}

        	if( Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)) ) 
        		return false;
        	else
        	{
        		i++; j--;
        	} 
*/
        	if(str.charAt(i) != str.charAt(j))
        		return false;
        	
        	i++;
        	j--;
        } // end of first while
        
        return true;
    }
}