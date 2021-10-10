package com.my.Tree;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/9 10:53 上午
 */
public class BinarySearchTree {
    public static boolean isValid = true;
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val){
            this.val = val;
        }
    }


    /**
     * 两种解法：
     * 1. 递归
     * 2. 中序遍历 ：递归/非递归
     */

    /**
        新的递归写法：二分法
        初始上下界为MAX和MIN
        理解，相当于从上至下，基于二叉树的每层二级节点，对（-∞， +∞）范围进行划分，如果能够正常划分，说明是搜索二叉树（搜索路径是顺畅的）；
        如果不能正常划分，出现了范围值矛盾，则说明不是BST
     **/
    public static boolean isValidRecur(TreeNode root){
        // 初始最大值最小值为全范围
        return isValidRecur(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static Boolean isValidRecur(TreeNode root, Integer min, Integer max){
        // 叶子节点的左子节点为null
        if(root == null){
            return true;
        }
        // 无法按照二叉树顺序对全范围空间进行划分时
        if(root.val >= min || root.val <= max){
            return false;
        }

        // 空间被划分为(-∞，root.val) 和 (root.val, +∞)
        Boolean isLeftBST = isValidRecur(root.left, min, root.val);
        Boolean isRightBST = isValidRecur(root.right, root.val, max);
        return isLeftBST && isRightBST;
    }


    /**
     * 中序遍历-递归
     * 需要两个全局变量：
     *  curVal，记录上一个节点的值；
     *  isValid, 记录是否为搜索树的结果
     * 存在问题：无法在发现isValid == false时停止递归
     */
    public long curVal = - Long.MAX_VALUE;
    public boolean isValidInorderRecur(TreeNode root){
        recur(root);
        return isValid;
    }
    public void recur(TreeNode head){
        if(isValid){
            recur(head.left);
            if(head.val <= curVal){
                isValid = false;
            }
            curVal = head.val;
        }
    }

    /**
     * 中序遍历-非递归
     * 不需要全局变量，可以在发现false时终止
     * @param root
     * @return
     */
    public static boolean isValidInorder(TreeNode root){
        int curVal = -Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();

        if(root == null){
            return true;
        }

        TreeNode cur = root;
        while(cur != null  || !stack.isEmpty()){
            while(cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            if(!stack.isEmpty()){
                cur = stack.pop();
                if(cur.val<= curVal){
                    return false;
                }
                curVal = cur.val;
                cur = cur.right;
            }
        }
        return true;
    }

    /**
     * 自己写的递归--写的太复杂了！！
     * 每次递归返回的是左子树和右子树的最大最小值
     * @param root
     * @return
     */
    public static boolean isValidBST(TreeNode root){
        checkTree(root);
        return isValid;
    }

    public static ArrayList<Integer> checkTree(TreeNode head){
        ArrayList<Integer> minAndMax = new ArrayList<>();
        if(head == null){
            return null;
        }

        ArrayList<Integer> leftTuple = checkTree(head.left);
        ArrayList<Integer> rightTuple = checkTree(head.right);

        if(leftTuple == null && rightTuple == null){
            minAndMax.add(head.val);
            minAndMax.add(head.val);
        } else if(leftTuple == null){
            if(!(head.val < rightTuple.get(0))){
                isValid = false;
                return null;
            }
            minAndMax.add(head.val);
            minAndMax.add(rightTuple.get(1));
        } else if(rightTuple == null){
            if(!(head.val > leftTuple.get(1))){
                isValid = false;
                return null;
            }
            minAndMax.add(leftTuple.get(0));
            minAndMax.add(head.val);
        } else {
            if(!(head.val > leftTuple.get(1) && head.val < rightTuple.get(0))){
                isValid = false;
                return null;
            }
            minAndMax.add(leftTuple.get(0));
            minAndMax.add(rightTuple.get(1));
        }
        return minAndMax;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(-1);
        TreeNode node2 = new TreeNode(-9);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(-10);
        TreeNode node5 = new TreeNode(45);

        node1.right = node2;
        node2.left = node3;
        node3.right = node4;
        node4.right = node5;

         /*
                       二叉树
                        -1
                       /   \
                      -9     7
                    /   \
                   -10    -8
                            \
                             -1

        */

        System.out.println(isValidBST(null));
        System.out.println(isValidBST(node1));

        System.out.println(isValidRecur(node1));

    }


}
