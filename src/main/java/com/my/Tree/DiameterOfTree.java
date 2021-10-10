package com.my.Tree;

/**
 * 二叉树的直径，不一定过根节点，如测试样例
 * 实现方法类似MaxSumOfPath
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 5:25 下午
 */
public class DiameterOfTree {
    public static int maxDiameter;
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }

    public static int getDiameterOfTree(TreeNode root){
        if(root == null){
            return 0;
        }
        maxDiameter = 0;
        getDiameter(root);
        return maxDiameter;
    }

    public static int getDiameter(TreeNode root){
        int diameter = 0;
        if(root == null){
            return diameter;
        }

        int leftGain = getDiameter(root.left);
        int rightGain = getDiameter(root.right);

        maxDiameter = Math.max(maxDiameter, leftGain + rightGain);

        return Math.max(leftGain, rightGain) + 1;
    }


    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(11);
        TreeNode node8 = new TreeNode(8);
        TreeNode node9 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.right = node6;
        node4.left = node7;
        node7.left = node9;
        node6.left = node8;
         /*
                       二叉树
                        -10
                       /   \
                      9     7
                    /   \
                   10    -2
                  /        \
                 11         1
                 /          /
                9          8
        */

        // [4,-7,-3,null,null,-9,-3,9,-7,-4,null,6,null,-6,-6,null,null,0,6,5,null,9,null,null,-1,-4,null,null,null,-2]

        /*
                           4
                          / \
                        -7  -3
                            / \
                          -9  -3
                         / \  /
                        9 -7 -4
         */

        System.out.println(getDiameterOfTree(node1));
    }
}
