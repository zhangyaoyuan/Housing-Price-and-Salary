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
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<TreeNode> ldl = new LinkedList<>();
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        TreeNode temp = root;
        while(temp != null || ldl.peekLast() != null){
            if(temp != null){
                ldl.addLast(temp);
                temp = temp.left;
            }
            else{
                    temp = ldl.pollLast();
                    result.add(new Integer(temp.val));
                    temp = temp.right;
                }
            }
        return result;
    }
}