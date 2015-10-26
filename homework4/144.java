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
        LinkedList<TreeNode> ldl = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        TreeNode temp = root;
        while(temp != null || ldl.peekLast() != null){
            if(temp != null){
		//先序遍历，先存数，再入栈
                result.add(new Integer(temp.val));
                ldl.addLast(temp);
		//遍历左子树
                temp = temp.left;
            }
            else{
		//退栈，遍历其右子树
                temp = ldl.pollLast();
                temp = temp.right;
            }
        }
        return result;
    }
}