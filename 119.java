public class Solution {
    public List<Integer> getRow(int rowIndex) {
       List<Integer> listResult = new LinkedList<Integer>();

        if(rowIndex == 0){
        	listResult.add(1);
        	return listResult;	
        }
        if(rowIndex == 1){
        	listResult.add(1);
        	listResult.add(1);
        	return listResult;
        }


        List<Integer> listTemp = new LinkedList<Integer>();
        listTemp.add(1);
        listTemp.add(1);
        
        int i = 0;
        
        for(i=1; i<rowIndex; i++){
        	int index = 0; 
            int back = 0;
            int front = 0;
            listResult.clear();
        	while(index <= i){
        		back = front;
        		front = listTemp.get(index);
        		listResult.add(back + front);
        		index++;
        	}
        	listResult.add(1);
        	
        	listTemp.clear();
        	for(int j=0; j<listResult.size(); j++){
        		listTemp.add(listResult.get(j));
        	}
        }
          
        return listResult;
    }
}