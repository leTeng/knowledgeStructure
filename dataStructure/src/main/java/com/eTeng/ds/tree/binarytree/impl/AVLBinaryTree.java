package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/8/2
 * @Description VAL的实现，基于标准的二叉树，对树的add() 或 remove() 通过条件进行平衡
 */
public class AVLBinaryTree<AnyType> extends MyBinarySearchTree<AnyType>{

    public AVLBinaryTree(){

    }
    /**
     * 支持使用自定义比较器
     * @param myComparator 自定义比较器
     */
    public AVLBinaryTree(MyComparator myComparator){
        super(myComparator);
    }

    /**
     *  只有insert() 或者 remove() 才需要进行树平衡，所以其他的操作
     */

    
    @Override
   	public void insert(AnyType anyType) {
   		root = makeInsert(anyType, root);
   	}
   	
    @Override
   	public AnyType remove(AnyType anyType) {
    	root = makeRemove(anyType, root);
    	return anyType;
    }

     /*
      * 最后返回值是新的根节点，并且每个节点的高度会更新
      */
     private AVLNode<AnyType> makeRemove(AnyType anyType , TreeNode<AnyType> node){
        return null;
     }

	/*
     * 最后返回值是新的根节点，并且每个节点的高度会更新
     */
     private TreeNode<AnyType> makeInsert(AnyType anyType , TreeNode<AnyType> node){

         if(node == null){
             return new AVLNode<AnyType>(anyType,null,null);
         }

         int result = compareTo(node.data,anyType);
         if(result < 0){
             node.left = makeInsert(anyType,node.left);
         }else if(result > 0){
             node.right = makeInsert(anyType,node.right);
         }else{
             //相同元素，更新动作
             node.data = anyType;
         }
         /*	进行平衡，node第一次是新插入的节点的父节点，
          *	   下一次node是上一个节点的父节点,彼此递归。
          *		最后返回平衡后最新的子树根节点，并更新到树节点中
          */
         return balance(node);
     }

    /*
     * 子树之间的最大高度差
     */
     private static final int ALLOW_HEIGHT_DIFF = 1;

     private TreeNode<AnyType> balance(TreeNode<AnyType> node){

         if(node == null){
             return node;
         }
         //判断失衡方式
         if(height(node.left) - height(node.right) > ALLOW_HEIGHT_DIFF){
            //左向节点不平衡
             if(height(node.left.left) >= height(node.left.right)){
                 //左-左式节点不平衡，单旋转
            	 node = rotateWithLeftChild(node);
             }else{
                 //左-右式节点不平衡,双旋转
            	 node = doubleWithLeftChild(node);		
             }
         //右向节点不平衡
         }else if(height(node.right) - height(node.left) > ALLOW_HEIGHT_DIFF){
             if(height(node.right.right) >= height(node.right.left)){
                //右-右式节点不平衡，单旋转
                 node = rotateWithRightChild(node);
             }else{
                //右-左式节点不平衡，双旋转
            	 node = doubleWithRightChild(node);
             }
         }
         //更新路径上的节点高度
         node.height = Math.max(height(node.left),height(node.right)) + 1;
         //返回最新的相对根节点
         return node;
     }

    /**
     * 左 -- 左 失衡的情况，将中间节点作为新的根节点。
     *          中间节点的右节点给第一个节点的左节点，
     *          第一个节点作为中间节点的右节点。
     *          同时更新中间节点和第一个节点的高度。
     * @param right 失衡节点（第一个节点）
     * @return
     */
     public AVLNode<AnyType> rotateWithLeftChild(TreeNode<AnyType> right){
         AVLNode<AnyType> left = (AVLNode<AnyType>) right.left;
         right.left = left.right;
         left.right = right;
         right.height = Math.max(height(right.left),height(right.right)) + 1;
         left.height = Math.max(height(left.left),height(right)) + 1;
         return left;
     }

    /**
     *  右--右 失衡的情况，将中间节点作为新的根节点。
     *          中间节点的左节点给第一个节点的右节点，
     *          第一个节点作为中间节点的左节点。
     *          同时更新中间节点和第一个节点的高度。
     * @param left 失衡节点（第一个节点）
     * @return
     */
    public TreeNode<AnyType> rotateWithRightChild(TreeNode<AnyType> left){
        AVLNode<AnyType> right = (AVLNode<AnyType>) left.right;
        left.right = right.left;
        right.left = left;
        left.height = Math.max(height(left.left),height(left.right)) + 1;
        right.height = Math.max(height(right.left),height(left)) + 1;
        return right;
    }

    /**
     * 	左--右 双旋转算法：
     * 		1.将失衡节点的左节点进行一次右--右单旋转并且更新子树的高度，
     * 			然后将子树的新根节点重新赋值给失衡节点的左节点。
     * 		2.失衡节点的左节点进行右--右单旋转后，将失衡节点进行一次左--左旋转，并更新高度。
     * 			返回的新子树节点是已经平衡好的子树根节点。
     * @param imbalance 失衡节点
     * @return
     */
    private TreeNode<AnyType> doubleWithLeftChild(TreeNode<AnyType> imbalance) {
    	//实现一次右--右单旋转
    	imbalance.left = rotateWithRightChild(imbalance.left);
    	//再次左--左旋转,返回新的相对新节点
    	return rotateWithLeftChild(imbalance);	
    }
    
    private TreeNode<AnyType> doubleWithRightChild(TreeNode<AnyType> imbalance) {
    	//实现一次左--左单旋转
    	imbalance.right = rotateWithLeftChild(imbalance.right);
    	//再次右--右旋转,返回新的相对新节点
    	return rotateWithRightChild(imbalance);	
    }
    /**
     * 节点的高度，如果为空节点，定义为 -1
     * @param left 节点
     * @return
     */
     private int height(TreeNode<AnyType> left){
         return left == null ? -1 : left.height;
     }

    /**
     *  AVLTree 特有的节点抽象，相比标准的二叉树，多了一个高度属性。
     *              这也是是否需要平衡节点的依据
     * @param <AnyType>
     */
    private static class AVLNode<AnyType> extends TreeNode<AnyType>{

        public AVLNode(AnyType data,AVLNode left,AVLNode right){
            this(data,left,right,0);
        }

        public AVLNode(AnyType data){
            this(data,null,null);
        }

        public AVLNode(AnyType data,AVLNode left,AVLNode right,int height){
           super(data,left,right,height);
        }
    }
}
