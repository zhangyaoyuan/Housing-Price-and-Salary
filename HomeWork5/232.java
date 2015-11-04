class MyQueue {
    Stack<Integer> myStackInt = new Stack<Integer>();
	Stack<Integer> myStackTmp = new Stack<Integer>();
	
    // Push element x to the back of queue.
    public void push(int x) {
        while(!myStackInt.isEmpty())
        	myStackTmp.push(myStackInt.pop());
        myStackInt.push(x);
        while(!myStackTmp.isEmpty())
        	myStackInt.push(myStackTmp.pop());
    }

    // Removes the element from in front of queue.
    public void pop() {
    	myStackInt.pop();
    }

    // Get the front element.
    public int peek() {
    	return myStackInt.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
     return myStackInt.isEmpty();   
    }
}