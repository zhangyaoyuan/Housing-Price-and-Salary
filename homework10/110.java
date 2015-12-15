public class Solution {
    public boolean isBalanced(TreeNode root) {
        //维护一个队列，每个节点入队列，判断每个节点的平衡性
        LinkedList<TreeNode> ll = new LinkedList<>();
        if(root != null)ll.add(root);
        while(!ll.isEmpty()){
            TreeNode temp = ll.pollFirst();
            if(temp.left != null){
                ll.add(temp.left);
            }
            if(temp.right != null){
                ll.add(temp.right);
            }
            if(Math.abs(depth(temp.left) - depth(temp.right)) > 1){
                return false;
            }
        }
        return true;
    }
    //求该结点的深度
    public int depth(TreeNode node){
        if(node == null){
            return 0;
        }
        return Math.max(depth(node.left),depth(node.right)) + 1;
    }
}