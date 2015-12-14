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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode tr = root;
        int nMin = Math.min(p.val, q.val);
        int nMax = Math.max(p.val, q.val);
        
        while( (tr != p) && (tr != q) ){
        	//两个节点在该节点的两端
        	if((tr.val < nMax) && (tr.val > nMin))
            	return tr;
        	
        	//都在左子树
        	if( tr.val > nMax)
        	{
        		tr = tr.left;
        		continue;
        	}
        	
        	//都在又子树
        	if( tr.val < nMin ) 
        	{
        		tr = tr.right;
        		continue;
        	}
        }
       
        return tr;
    }
}