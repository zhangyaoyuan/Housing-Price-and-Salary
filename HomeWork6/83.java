/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
		if(head == null) return head;
        ListNode nodePre = head;
        ListNode nodeCur = head;
        while(nodeCur.next != null)
        {
        	nodeCur = nodeCur.next;
        	if(nodePre.val == nodeCur.val)
        	{
        		nodePre.next = nodeCur.next;
        		nodeCur = nodePre;
        	}
        	else
        	{
        		nodePre = nodeCur;
        	}
        }
		return head;
    }
}