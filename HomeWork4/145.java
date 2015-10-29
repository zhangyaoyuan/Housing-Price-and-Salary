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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> listInt = new LinkedList<Integer>();
        if(root == null) return listInt;
        TreeNode pCurNode = root;
        Stack<TreeNode> stackNode = new Stack<TreeNode>();
        boolean bFirst = true;
        
        while(!stackNode.isEmpty() || bFirst)  //后续遍历 应该以栈为空作为退出条件
        {
        	bFirst = false;
        	if(pCurNode.left != null || pCurNode.right != null)
        	{
        		stackNode.push(pCurNode);
        		if(pCurNode.left != null)
            		pCurNode = pCurNode.left;
            	else if(pCurNode.right != null)
            		pCurNode = pCurNode.right;
        		continue;
        	}
        	
        	listInt.add(pCurNode.val);
        	if(!stackNode.isEmpty() && pCurNode == stackNode.peek().left)  //如果是左子树返回
        	{
        		while(!stackNode.isEmpty() && (stackNode.peek().right == null || 
            			pCurNode == stackNode.peek().right) )
        			listInt.add((pCurNode = stackNode.pop()).val);
        		
        		if(!stackNode.isEmpty() && stackNode.peek().right != null)
        		{
        			pCurNode = stackNode.peek().right;
        			continue;
        		}
        	}
        	
        	if(!stackNode.isEmpty() && pCurNode == stackNode.peek().right) //如果是右子树返回
        	{
        		listInt.add((pCurNode = stackNode.pop()).val);
        		while(!stackNode.isEmpty() && (stackNode.peek().right == null ||
            			stackNode.peek().right == pCurNode)) 
	        		listInt.add((pCurNode = stackNode.pop()).val);
        		
        		if(!stackNode.isEmpty() && stackNode.peek().right != null)
        		{
        			pCurNode = stackNode.peek().right;
        			continue;
        		}
        	}
        } // end of while
        return listInt;
    }
}