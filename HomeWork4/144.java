/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> listInt = new LinkedList<Integer>();
		if( root == null ) return listInt;
        Stack<TreeNode> stackNode = new Stack<TreeNode>();
        TreeNode pCurNode = root;
        
        while(pCurNode != null)
        {
        	listInt.add(pCurNode.val);
        	stackNode.push(pCurNode);
    		
        	if(pCurNode.left != null)
        		pCurNode = pCurNode.left;
        	else
        		while(!stackNode.isEmpty() && (pCurNode = stackNode.pop().right) == null) ;
        }
        
		return listInt;
    }
}