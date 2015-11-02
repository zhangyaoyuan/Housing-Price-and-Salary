class MyStack {
    // Push element x onto stack.
    ArrayDeque<Integer> q1 = new ArrayDeque<>();
    ArrayDeque<Integer> q2 = new ArrayDeque<>();
    public void push(int x) {
        if(q2.isEmpty()){
            q1.add(x);
        }else{
            q2.add(x);
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        Integer temp;
        if(!q1.isEmpty()){
            while(!q1.isEmpty()){
                 temp = q1.poll();
                 if(!q1.isEmpty()){
                    q2.add(temp);
                 }
            }
        }else{
            while(!q2.isEmpty()){
                temp = q2.poll();
                if(!q2.isEmpty()){
                    q1.add(temp);
                }
            }
        }
    }

    // Get the top element.
    public int top() {
        Integer temp = null;
        if(!q1.isEmpty()){
            while(!q1.isEmpty()){
                temp = q1.poll();
                q2.add(temp);
            }
        }else{
            while(!q2.isEmpty()){
                temp = q2.poll();
                q1.add(temp);
                
            }
        }
        return temp.intValue();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}