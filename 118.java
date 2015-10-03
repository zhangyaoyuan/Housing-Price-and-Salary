public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listResult = new LinkedList<List<Integer>>();
		
		if(numRows == 0)
			return listResult;
		
		int back = 1;
		int front = 1;
		
		List<Integer> listRow1 = new LinkedList<Integer>();
		listRow1.add(1);
		
		List<Integer> listRow2 = new LinkedList<Integer>();
		listRow2.add(1);
		listRow2.add(1);
		
		listResult.add(listRow1);
		if(numRows == 1){
			return listResult;
		}
		
		listResult.add(listRow2);
		if(numRows == 2){
			return listResult;
		}
		
		int i = 2;
		int index = 0;
		for(; i<numRows; i++){
			List<Integer> listInt = new LinkedList<Integer>();
			List<Integer> listTemp = listResult.get(i-1);
			int nTempLen = listTemp.size();
			back = 0;
			front = 0;
			index = 0;
			while(index < nTempLen){
				back = front;
				front = listTemp.get(index);
				listInt.add(back+front);
				index++;			
			}
			listInt.add(1);
			
			listResult.add(listInt);
		}
		
		return listResult;
    }
}