package com.my.Tree;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/8 5:38 下午
 */
public class MergeTwoTrees {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
        public TreeNode(){
        }
    }

    public static TreeNode mergeTwoTrees(TreeNode root1, TreeNode root2){
        if(root1 == null){
            return root2;
        } else if(root2 == null){
            return root1;
        }

        TreeNode result = new TreeNode();
        result.val = root1.val + root2.val;
        result.left = mergeTwoTrees(root1.left, root2.left);
        result.right = mergeTwoTrees(root1.right, root2.right);

        return result;
    }

}
