class MyStack {
    Queue<Integer> myQueue = new LinkedList<Integer>();
	Queue<Integer> myQueueTmp = new LinkedList<Integer>();
	
    // Push element x onto stack.
    public void push(int x) {
    	while(!myQueue.isEmpty())
    		myQueueTmp.add(myQueue.poll());
    	myQueue.add(x);
    	while(!myQueueTmp.isEmpty())
    		myQueue.add(myQueueTmp.poll());
    }

    // Removes the element on top of the stack.
    public void pop() {
        myQueue.poll();
    }

    // Get the top element.
    public int top() {
        return myQueue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return myQueue.isEmpty();
    }
}