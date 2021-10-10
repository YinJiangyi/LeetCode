package com.my.Tree;

import java.lang.reflect.Array;
import java.util.*;

/**
 * (此方法不对！) 中序遍历 == 变形中序遍历
 * 需要带null输出才可以
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 2:38 下午
 */
public class SymmetricTree {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }

    public static boolean isSymmetricTree(TreeNode head){
        if(head != null){
            return isSymmetricTrees(head.left, head.right);
        } else {
            return false;
        }
    }

    public static boolean isSymmetricTrees(TreeNode head1, TreeNode head2){
        if(head1 == null && head2 == null){
            return true;
        } else if(head1 == null || head2 == null || head1.val!=head2.val){
            return false;
        } else {
            return (isSymmetricTrees(head1.left, head2.right) && isSymmetricTrees(head1.right, head2.left));
        }

    }


    /**
     * 遍历后比较
     * @param head
     * @return
     */
    public static boolean isSymmetricTree1(TreeNode head){
        if(head == null){
            return false;
        } else{
            System.out.println(levelReversalwithNull(head));
            System.out.println(levelReversalwithNullReverse(head));
            return levelReversalwithNull(head).equals(levelReversalwithNullReverse(head));
        }
    }

    public static List<Integer>levelReversalwithNull(TreeNode head){
        ArrayList<Integer>result = new ArrayList<>();
        Queue<TreeNode>queue = new LinkedList<>();

        queue.offer(head);
        TreeNode cur = head;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                result.add(null);
            } else {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return result;
    }

    public static List<Integer>levelReversalwithNullReverse(TreeNode head){
        ArrayList<Integer>result = new ArrayList<>();
        Queue<TreeNode>queue = new LinkedList<>();

        queue.offer(head);
        TreeNode cur = head;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                result.add(null);
            } else {
                result.add(node.val);
                queue.offer(node.right);
                queue.offer(node.left);
            }
        }

        return result;
    }

//    public static boolean isSymmetricTree(TreeNode head){
//        boolean result = false;
//        if(head != null){
//            List<Integer> middleOrder = middleOrder(head);
//            List<Integer> middle1Order = middle1Order(head);
//            System.out.println(middleOrder);
//            System.out.println(middle1Order);
//            result = (middle1Order.equals(middleOrder));
//        }
//        return result;
//    }
//
//    public static List<Integer>middleOrder(TreeNode head){
//        ArrayList<Integer>result = new ArrayList<>();
//        Stack<TreeNode>stack = new Stack<>();
//
//        TreeNode cur = head;
//        while(cur!=null || !stack.isEmpty()){
//            while(cur != null){
//                stack.push(cur);
//                cur = cur.left;
//            }
//            if(!stack.isEmpty()){
//                TreeNode pop = stack.pop();
//                result.add(pop.val);
//                cur = pop.right;
//            }
//        }
//
//        return result;
//    }
//
//    public static List<Integer>middle1Order(TreeNode head){
//        ArrayList<Integer>result = new ArrayList<>();
//        Stack<TreeNode>stack = new Stack<>();
//
//        TreeNode cur = head;
//        while(cur!=null || !stack.isEmpty()){
//            while(cur != null){
//                stack.push(cur);
//                cur = cur.right;
//            }
//            if(!stack.isEmpty()){
//                TreeNode pop = stack.pop();
//                result.add(pop.val);
//                cur = pop.left;
//            }
//        }
//
//        return result;
//    }


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
         /*
         [9,25,25,null,-95,-95,null,-100,null,null,-15]
                       二叉树
                         9
                       /   \
                      25     25
                        \     /
                        -95 -95
                      /       \
                   -100       -15
            */

//        System.out.println(isSymmetricTree(node1));


        System.out.println(isSymmetricTree(node1));
    }
}
