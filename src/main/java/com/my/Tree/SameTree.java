package com.my.Tree;

/**
 * 相同的树，本质考察的还是树的遍历
 *
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 2:12 下午
 */
public class SameTree {

    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 相同的树，本质考察的还是遍历
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isSameTree(TreeNode head1, TreeNode head2){
        if(head1 == null && head2 == null){
            return true;
        } else if(head1 == null || head2 == null || head1.val != head2.val){
            return false;
        }

        return (isSameTree(head1.left, head2.left) && isSameTree(head1.right, head2.right));
    }


    public static void main(String[] args){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        node4.right = node5;

        /**
         *          1
         *         / \
         *        1   3
         *         \
         *          4
         *           \
         *            5
         */

        System.out.println(isSameTree(node1, node1));
        System.out.println(isSameTree(node1, null));
        System.out.println(isSameTree(null, null));
    }


}
