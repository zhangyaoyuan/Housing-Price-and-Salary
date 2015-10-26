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
        //利用LinkedList作为栈
        LinkedList<TreeNode> ldl = new LinkedList<>();
        //利用HashMap来记录该结点是否已经访问过左右子结点
        HashMap<TreeNode,Boolean> hm = new HashMap<>();
        List<Integer> result = new LinkedList<>();
        if(root == null)return result;
        TreeNode temp = root;
        //栈和当前结点都为空，结束
        while(temp != null || ldl.peekLast() != null){
            if(temp != null){
                //如果没有访问过该结点
                if(!hm.containsKey(temp)){ 
                    //添加到Map
                    hm.put(temp,false);
                    //入栈
                    ldl.addLast(temp);
                    //遍历左子树
                    temp = temp.left;
                }else{
                    //访问过该结点,遍历右子树
                    temp = temp.right;
                }
            }
            else{
                //得到栈顶结点，不退栈
                temp = ldl.peekLast();
                //如果没有访问其右子树
                if(hm.get(temp) == false){
                    //将其value值置为true，访问其右子树
                    hm.put(temp,true);
                    temp = temp.right;
                }else{
                    //如果访问过其右子树，输出该结点的value值
                    result.add(new Integer(temp.val));
                    //将该结点从栈中去除，并得到新的栈顶结点，不退栈
                    ldl.pollLast();
                    temp = ldl.peekLast(); 
                }
            }
        }
        return result;
    }
}