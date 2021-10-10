package com.my.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 5:10 下午
 */
public class RightSightView {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }

    public static List<Integer>rightSightView(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        Queue<TreeNode>queue = new LinkedList<>();
        if(root == null){
            return result;
        }

        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            result.add(queue.peek().val);
            while(size > 0){
                TreeNode node = queue.poll();
                if(node.right != null){
                    queue.offer(node.right);
                }
                if(node.left != null){
                    queue.offer(node.left);
                }
                size --;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-10);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(10);
        TreeNode node5 = new TreeNode(-2);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node5.right = node6;
         /*
                       二叉树
                        -10
                       /   \
                      9     7
                    /   \
                   10    -2
                          \
                           1
        */


        System.out.println(rightSightView(node2));
    }

}
