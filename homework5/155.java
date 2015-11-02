class MinStack {
    class ListNode{
        int val;
        int min;
        ListNode next;
        
        ListNode(int x, int min){
            this.val = x;
            this.min = min;
            next = null;
        }
    }
    ListNode head;
    public void push(int x) {
        if(head == null){
          head= new ListNode(x,x);
        }else{
            ListNode temp = new ListNode(x,Math.min(x,head.min));
            temp.next = head;
            head = temp;
        }
    }

    public void pop() {
        if(head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = null;
        }
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
