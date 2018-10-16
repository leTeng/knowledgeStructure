package com.eTeng.ds.tree.binarytree.impl;

/**
 * @FileName
 * @Author eTeng
 * @Date 2018/8/2
 * @Description 基础的树节点数据抽象
 */
class TreeNode<AnyType>{

    AnyType data; //元素数据
    TreeNode<AnyType> left; //左子树
    TreeNode<AnyType> right; //右子树
    int height; //节点的高度

    public TreeNode(AnyType data) {
        this(data,null,null);
    }

    public TreeNode(AnyType data , TreeNode<AnyType> left,
                    TreeNode<AnyType> right) {
      this(data,left,right,0);
    }
    
    public TreeNode(AnyType data , TreeNode<AnyType> left,
            TreeNode<AnyType> right,int height) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.height = height;
}
}
