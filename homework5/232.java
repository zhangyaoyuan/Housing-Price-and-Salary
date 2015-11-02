class MyQueue {
    // Push element x to the back of queue.
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    public void push(int x) {
         s1.push(x);
    }

    // Removes the element from in front of queue.
    //取s1栈底元素，借助中介s2，最后元素重新入栈s1
    public void pop() {
        Integer temp = null;
        while(!s1.empty()){
            temp = s1.pop();
            if(!s1.empty()){
                s2.push(temp);
            }
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
    }

    // Get the front element.
    //取s1栈底元素，借助中介s2，最后元素重新入栈s1
    public int peek() {
        Integer temp = null;
        while(!s1.empty()){
            temp = s1.pop();
            s2.push(temp);
        }
        while(!s2.empty()){
            s1.push(s2.pop());
        }
        return temp;
    }

    // Return whether the queue is empty.
    //s1,s2都为空，队列为空
    public boolean empty() {
        return s1.empty() && s2.empty();
    }
}