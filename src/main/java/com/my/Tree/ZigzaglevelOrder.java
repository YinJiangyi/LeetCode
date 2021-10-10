package com.my.Tree;

import java.util.*;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/8 11:20 上午
 */
public class ZigzaglevelOrder {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 层序遍历 + 双端队列
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                // 用双端队列实现 栈和队列共存的操作
                // push to last = offerLast    push to top = offerFirst
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }


    /**
     * 层序遍历 + 栈
     * @param root
     * @return
     */
    public static List<List<Integer>>zigZagLevelOrder(TreeNode root){
        ArrayList<List<Integer>>result = new ArrayList<>();
        if(root == null){
            return result;
        }
        ArrayList<Integer>levelList = new ArrayList<>();

        Queue<TreeNode>queue = new LinkedList<>();
        Stack<Integer>stack = new Stack<>();
        queue.offer(root);
        int rowNum = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size >0) {
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }

                // 奇数行从左向右，不加栈
                if(rowNum % 2 == 1){
                    levelList.add(node.val);
                    // 偶数行从右向左，加栈
                } else {
                    stack.push(node.val);
                }
                size --;
            }

            if(rowNum % 2 ==0){
                while(!stack.isEmpty()){
                    levelList.add(stack.pop());
                }
            }

            rowNum += 1;
            result.add(levelList);
            levelList = new ArrayList<>();
        }

        return result;
    }

    public static void main(String[] args){
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
        node4.left = new TreeNode(11);
         /*
                       二叉树
                         8
                       /   \
                      9     7
                    /   \
                   10    2
                  /       \
                 11        1
            */


        System.out.println(zigZagLevelOrder(node1));
        System.out.println(zigZagLevelOrder(null));
        System.out.println(zigZagLevelOrder(node2));
    }
}
