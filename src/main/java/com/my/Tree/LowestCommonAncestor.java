package com.my.Tree;

/**
 * @author yjy
 * @version 1.0
 * @date 2021/10/11 3:56 下午
 */


/**
 * 前序遍历—从上往下查找，只有当出现p或q时，返回节点，否则返回null
 * 只有当
 */
public class LowestCommonAncestor {
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return null;
        }
        if(root.val == p.val || root.val == q.val){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left != null && right != null){
            return root;
        } else if(left != null){
            return left;
        } else if(right != null){
            return right;
        }
        return null;
    }

}
