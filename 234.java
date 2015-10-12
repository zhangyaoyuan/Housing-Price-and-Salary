/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;

//        ListNode pointCurNode = head;
//        
//        StringBuffer strBufOrig = new StringBuffer();
//        while(pointCurNode != null){
//        	strBufOrig.append(pointCurNode.val);
//        	strBufOrig.append(" ");
//        	pointCurNode = pointCurNode.next;
//        }
		StringBuffer strBufOrig = new StringBuffer();
		StringBuffer strBufReves = new StringBuffer();
		ListNode l, mid, r;
		
		l = head; mid = head; r = head.next;
		while(mid != null)
		{
			strBufOrig.append(mid.val);
			mid.next = l;
			l = mid; 
			mid = r;
			if(mid == null)
			{
				head.next = null;
				head = l;	
			}
			else
				r = r.next;
		}
		
		l = head; mid = head; r = head.next;
		while(mid != null)
		{
			strBufReves.append(mid.val);
			mid.next = l;
			l = mid; 
			mid = r;
			if(mid == null)
			{
				head.next = null;
				head = l;	
			}
			else
				r = r.next;
		}
        
		return strBufOrig.toString().equals(strBufReves.toString());
    }
}