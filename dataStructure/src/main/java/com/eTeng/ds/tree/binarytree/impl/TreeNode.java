package com.eTeng.ds.tree.binarytree.impl;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/8/2
 * @Description 基础的树节点数据抽象
 */
class TreeNode<AnyType>{

    AnyType data; //元素数据
    TreeNode<AnyType> left; //左子树
    TreeNode<AnyType> right; //右子树

    public TreeNode(AnyType data) {
        this(data,null,null);
    }

    public TreeNode(AnyType data , TreeNode<AnyType> left,
                    TreeNode<AnyType> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
