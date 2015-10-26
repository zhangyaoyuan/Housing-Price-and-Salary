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
    public int minDepth(TreeNode root) {
        if(root == null)return 0;
        int depth = 1;
        //建立队列，使树的结点逐一入队列
        LinkedList<TreeNode> ldl = new LinkedList<>();
        ldl.addLast(root);
        TreeNode temp;
        //记录上一层的结点数
        int last_count = 1;
        //记录这一层的结点数
        int this_count = 0;
        while((temp = ldl.pollFirst()) != null){
            //队列出一个结点,上一层的结点数减一
            --last_count;
            //此节点没有子结点，已经到了叶子结点
            if(temp.left == null && temp.right == null)return depth;
            //存在左孩子，入队列，此层结点数加一
            if(temp.left != null){
                ldl.addLast(temp.left);
                this_count += 1;
                
            }
            //存在右孩子，入队列，此层结点数加一
            if(temp.right != null){
                ldl.addLast(temp.right);
                this_count += 1;
            }
            //如果此结点为上一层最后一个结点，深度加一
            if(last_count == 0){
                last_count = this_count;
                this_count = 0;
                depth += 1;
            }
        }
        return depth;
    }
}