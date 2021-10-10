package com.my.Tree;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 5:54 下午
 */
public class BuildTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }

    public static TreeNode buildTree(int[] preorder, int[] inorder){

        if(preorder.length!= inorder.length || preorder.length == 0){
            return null;
        }
        if(preorder.length == 1){
            return new TreeNode(preorder[0]);
        }

        TreeNode head = new TreeNode(preorder[0]);
//        int headIndex = 0;
//        for(int index = 0; index < inorder.length; index++){
//            if(inorder[index] == head.val){
//                headIndex = index;
//                break;
//            }
//        }

        // 构造哈希映射，帮助我们快速定位根节点
        HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        int headIndex = indexMap.get(head.val);


        head.left = buildTree(
                Arrays.copyOfRange(preorder, 1, 1 + headIndex),
                Arrays.copyOfRange(inorder, 0, headIndex));
        head.right = buildTree(
                Arrays.copyOfRange(preorder, 1 + headIndex, preorder.length),
                Arrays.copyOfRange(inorder, headIndex + 1, inorder.length));

        return head;
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

        TreeNode x = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        System.out.println(x);
    }

}
