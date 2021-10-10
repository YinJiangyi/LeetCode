package com.my.Tree;

/** ☆☆☆☆☆☆☆☆☆☆  HARD ☆☆☆☆☆☆☆☆☆☆
 * 最大路径和，需要考虑根节点为负值的情况的情况
 * @author yjy
 * @version 1.0
 * @date 2021/10/6 4:11 下午
 */
public class MaxSumOfPath {
    public static int maxSum;
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(){}

        public TreeNode(int val){
            this.val = val;
        }

    }


    public static int getMaxSum(TreeNode root){
        assert(root==null);
        maxSum = root.val;
        MaxSum(root);
        return maxSum;
    }

    /*
     Max返回值是经过当前节点的、单侧的最大值，也就是所谓的"贡献值"，能继续向上连
     定义的类全局变量maxSum存储最大值，实际上是在通过递归进行"贡献值"计算的过程中，把两侧的值也一起算出来了，即
          每个节点出计算一个经由当前节点的最大路径和，用curSum保存最大值
     总结一下：
          MaxSum实际上通过一次递归实现了两个功能：
          1) 向上延伸计算路径和，和一开始的错误做法getMaxSumOfOnePath功能一样
          2) 维护一个变量curSum保存遍历到的每个点存在的路径中的最大和。这种主要应对的情况是，已经完成递归回到root了，发现和最大的是较低层的点。
             如：
                   -10
                   /  \
                  -7   -5
                       / \
                     -1   9
                         / \
                        2   6
               这种情况下，在递归到节点9时，计算出的 curSum = root.val + leftGain + rightGain 得以保存在curSum当中。

     这种递归函数中维护全局变量的思路需要好好理解。
     */

    public static int MaxSum(TreeNode root){
        if(root != null) {

            int leftGain = Math.max(0, MaxSum(root.left));
            int rightGain = Math.max(0, MaxSum(root.right));

            int curSum = root.val + leftGain + rightGain;

            maxSum = Math.max(curSum,maxSum);
            return Math.max(leftGain, rightGain) + root.val;
        }
        return 0;
    }

//    public static int getMaxSumOfPath(TreeNode root){
//        if(root == null){
//            return 0;
//        }
//        return getMaxSumOfOnePath(root.left) + getMaxSumOfOnePath(root.right) + root.val;
//    }
//
//
//    public static int getMaxSumOfOnePath(TreeNode root){
//        if(root == null){
//            return 0;
//        }
//
//        int leftSum = 0;
//        int rightSmm = 0;
//
//        if(root.left != null){
//            leftSum = getMaxSumOfOnePath(root.left);
//        }
//        if(root.right != null){
//            rightSmm = getMaxSumOfOnePath(root.right);
//        }
//
//        return Math.max(leftSum , rightSmm) + root.val;
//    }

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


        System.out.println(getMaxSum(node1));
//        System.out.println(getMaxSum(null));
        System.out.println(getMaxSum(node5));
    }

}
