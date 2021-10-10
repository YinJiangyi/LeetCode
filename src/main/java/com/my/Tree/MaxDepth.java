package com.my.Tree;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 3:59 下午
 */
public class MaxDepth {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }


    public static int getMaxDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftDepth = 0;
        int rightDepth = 0;

        if(root.left != null){
            leftDepth = getMaxDepth(root.left);
        }
        if(root.right != null){
            rightDepth = getMaxDepth(root.right);
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.right = node6;
         /*
                       二叉树
                         8
                       /   \
                      9     7
                    /   \
                   10    2
                          \
                           1
        */


        System.out.println(getMaxDepth(node1));
        System.out.println(getMaxDepth(null));
        System.out.println(getMaxDepth(node5));
    }

}
