package com.my.Tree;

import java.util.*;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/4 5:59 下午
 */
public class OrderReversal {
    /**
     * 静态类-二叉树
     */
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 前序遍历-递归
     * @param root
     * @return
     */
    public static List<Integer> preOrderRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        preRecur(root, result);

        return result;
    }

    private static void preRecur(TreeNode root, ArrayList<Integer>result){
        if(root != null){
            result.add(root.val);
            preRecur(root.left, result);
            preRecur(root.right, result);
        }
    }

    /**
     * 前序遍历-非递归
     */
    public static List<Integer> preOrderNonRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 把根节点入栈
        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            result.add(cur.val);
            if(cur.right != null){
                stack.push(cur.right);
            }
            if(cur.left != null){
                stack.push(cur.left);
            }
        }
        return result;
    }


    /**
     * 中序遍历-递归
     */
    public static List<Integer>middleOrderRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        middleRecur(root, result);

        return result;
    }

    private static void middleRecur(TreeNode root, ArrayList<Integer>list){
        if(root != null){
            middleRecur(root.left, list);
            list.add(root.val);
            middleRecur(root.right, list);
        }
    }

    /**
     * 中序遍历-非递归
     * 每遍历到一个当前子树根节点，先将子树的每层左子节点入栈，然后转向右子树
     */
    public static List<Integer>middleOrderNonRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        Stack<TreeNode>stack = new Stack<>();

        TreeNode cur = root;
        // 对每个子树的根节点-步骤1
        while((cur != null) | (!stack.empty())) {
            // 子树根节点一直向左并将沿途节点压入堆栈-步骤2
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // 子树根节点出栈，指向右子树根节点，返回至步骤1
            if(!stack.empty()){
                cur = stack.pop();
                result.add(cur.val);
                cur = cur.right;
            }
        }

        return result;
    }

    /**
     * 中序遍历-非递归无辅助栈
     * @param
     */
    public static List<Integer>middleOrderNonRecurNonStack(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        // TODO
        return result;
    }

    /**
     * 后续遍历-递归
     * @param
     */
    public static List<Integer>postOrderRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();
        postRecur(root, result);

        return result;
    }

    private static void postRecur(TreeNode root, ArrayList<Integer>list){
        if(root != null){
            postRecur(root.left, list);
            postRecur(root.right, list);
            list.add(root.val);
        }
    }

    /**
     * 后续遍历-非递归
     * - 一路向左，节点入栈，同时标记0
     * - （第一次位于栈顶时，要压入右子节点；并修改标志位，表示曾经位于栈顶过，下次位于栈顶时直接输入）
     * - 最近节点标志位为0时，出栈栈顶元素，并修改标志位后重新压入；并压入其右子节点（出栈）
     **/
    public static List<Integer>postOrderNonRecur(TreeNode root){
        ArrayList<Integer>result = new ArrayList<>();

        Stack<TreeNode>stack = new Stack<>();
        Stack<Integer>stackFlag = new Stack<>();

        TreeNode cur = root;
        while((cur != null) | (!stack.isEmpty())){
            while(cur != null){
                stack.push(cur);
                stackFlag.push(0);
                cur = cur.left;
            }

            // 栈顶元素已经处理过右子树
            // 这里判断条件必须写 &&，否则stackFlag为空时，仍然会计算第二个条件，导致报错
            //a&b与a&&b的区别：a&b——a与b都计算，a&&b——先计算a如果a为false则不计算b。
            //a|b与a||b的区别：a|b——a与b都计算，a||b——先计算a如果a为true则不计算b。
            while((!stackFlag.isEmpty()) && (stackFlag.peek() == 1)){
                result.add(stack.pop().val);
                stackFlag.pop();
            }
            // 栈顶元素未处理过右子树
            if(!stack.isEmpty()){
                cur = stack.peek();
                // 修改标志位
                stackFlag.pop();
                stackFlag.push(1);
                // 指向右子树
                cur = cur.right;
            }

        }

        return result;
    }

    /**
     * 层序遍历打印序列
     * 层序遍历 = 广度优先 BFS
     * @param head
     * @return
     */
    public static List<Integer> levelReversalNonRecur(TreeNode head){
        ArrayList<Integer>result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        if(head == null){
            return result;
        }
        while(!queue.isEmpty()){
            TreeNode cur = queue.poll();
            result.add(cur.val);
            if(cur.left != null){
                queue.offer(cur.left);
            }
            if(cur.right != null){
                queue.offer(cur.right);
            }
        }

        return result;
    }

    /**
     * 层序遍历-按层数输出数组 [[8], [9, 7], [10, 2], [1]]
     * @param root
     * @return
     */
    public static List<List<Integer>>levelReversal(TreeNode root){

        ArrayList<List<Integer>>result = new ArrayList<>();
        ArrayList<Integer>levelResult = new ArrayList<>();
        ArrayList<TreeNode>levelNode = new ArrayList<>();
        ArrayList<TreeNode>tmp = new ArrayList<>();

        if(root == null){
            return result;
        }

        TreeNode cur = root;
        levelNode.add(cur);
        result.add(Arrays.asList(cur.val));

        while(levelNode.size()>0) {
            for (TreeNode node : levelNode) {
                if (node.left != null) {
                    tmp.add(node.left);
                }
                if (node.right != null) {
                    tmp.add(node.right);
                }
            }

            for (TreeNode node : tmp) {
                levelResult.add(node.val);
            }
            if(levelResult.size()>0){
                result.add(levelResult);
            }
            levelNode = tmp;
            tmp = new ArrayList<>();
            levelResult = new ArrayList<>();

        }

        return result;
    }

    /**
     * 层序遍历-按层数输出数组   队列实现
     */
    public static List<List<Integer>> levelReversalQueue(TreeNode root){
        ArrayList<List<Integer>>result = new ArrayList<>();
        if(root == null){
            return result;
        }

        ArrayList<Integer>curResult;
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            // 用length标识本层节点的个数，初始化为root层即1
            int length = queue.size();
            curResult = new ArrayList<>();
            while(length > 0){
                TreeNode cur = queue.poll();
                if(cur.left != null){
                    queue.offer(cur.left);
                }
                if(cur.right != null){
                    queue.offer(cur.right);
                }
                length --;
                curResult.add(cur.val);
            }
            result.add(curResult);
        }

        return result;
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

        /**
         * 前序
         */
//        System.out.println(preOrderRecur(node1));
//        System.out.println(preOrderRecur(node3));
//        System.out.println(preOrderRecur(node5));
//        System.out.println(preOrderRecur(null));
//
//        System.out.println(preOrderNonRecur(node1));
//        System.out.println(preOrderNonRecur(node3));
//        System.out.println(preOrderNonRecur(node5));
//        System.out.println(preOrderNonRecur(null));

        /**
         * 中序
         */
//        System.out.println(middleOrderRecur(node1));
//        System.out.println(middleOrderRecur(node3));
//        System.out.println(middleOrderRecur(node5));
//        System.out.println(middleOrderRecur(null));

        System.out.println(middleOrderNonRecur(node1));
        System.out.println(middleOrderNonRecur(node3));
        System.out.println(middleOrderNonRecur(node5));
        System.out.println(middleOrderNonRecur(null));

        /**
         * 后序
         */
//        System.out.println(postOrderRecur(node1));
//        System.out.println(postOrderRecur(node3));
//        System.out.println(postOrderRecur(node5));
//        System.out.println(postOrderRecur(null));
//
//        System.out.println(postOrderNonRecur(node1));
//        System.out.println(postOrderNonRecur(node3));
//        System.out.println(postOrderNonRecur(node5));
//        System.out.println(postOrderNonRecur(null));

    }
}
