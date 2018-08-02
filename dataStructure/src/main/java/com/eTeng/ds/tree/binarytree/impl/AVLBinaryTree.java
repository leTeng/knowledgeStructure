package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/8/2
 * @Description VAL的实现，基于标准的二叉树，对树的add() 或 remove() 通过条件进行平衡
 */
public class AVLBinaryTree<AnyType> extends MyBinarySearchTree<AnyType>{

    private AVLNode<AnyType> root;

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


     /*
      * 最后返回值是新的根节点，并且每个节点的高度会更新
      */
     private AVLNode<AnyType> makeRemove(AnyType anyType , AVLNode<AnyType> node){
        return null;
     }

    /*
     * 最后返回值是新的根节点，并且每个节点的高度会更新
     */
     private AVLNode<AnyType> makeInsert(AnyType anyType , AVLNode<AnyType> node){

         if(node == null){
             new AVLNode<AnyType>(anyType,null,null);
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
         return node;
     }

    /*
     * 子树之间的最大高度差
     */
     private static final int ALLOW_HEIGHT_DIFF = 1;

     private AVLNode<AnyType> balance(AVLNode<AnyType> node){

         if(node == null){
             return node;
         }
         //左子树出现不平衡
         if(height(node.left) - height(node.right) > ALLOW_HEIGHT_DIFF){
            //同向节点不平衡
             if(height(node.left.left) >= height(node.left.right)){
                 //左单旋转
                 rotateWithLeftChild(node);
             }else{
                 //反向节点不平衡,双旋转

             }
         //右子树出现不平衡
         }else if(height(node.right) - height(node.left) > ALLOW_HEIGHT_DIFF){
             if(height(node.right.right) >= height(node.right.left)){
                //左单旋转
                 rotateWithRightChild(node);
             }else{
                //双旋转
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
     * @param imBalanceNode
     * @return
     */
     public AVLNode<AnyType> rotateWithLeftChild(AVLNode<AnyType> imBalanceNode){
         AVLNode<AnyType> left = imBalanceNode.left;
         imBalanceNode.left = left.right;
         left.right = imBalanceNode;
         imBalanceNode.height = Math.max(height(imBalanceNode.left),height(imBalanceNode.right)) + 1;
         left.height = Math.max(height(left.left),height(imBalanceNode)) + 1;
         return left;
     }


    public AVLNode<AnyType> rotateWithRightChild(AVLNode<AnyType> imBalanceNode){
        AVLNode<AnyType> right = imBalanceNode.right;
        imBalanceNode.right = right.left;
        right.left = imBalanceNode;
        imBalanceNode.height = Math.max(height(imBalanceNode.left),height(imBalanceNode.right)) + 1;
        right.height = Math.max(height(right.left),height(imBalanceNode)) + 1;
        return right;
    }

    /**
     * 节点的高度，如果为空节点，定义为 -1
     * @param node 节点
     * @return
     */
     private int height(AVLNode<AnyType> node){
         return node == null ? -1 : node.height;
     }


    /**
     *  AVLTree 特有的节点抽象，相比标准的二叉树，多了一个高度属性。
     *              这也是是否需要平衡节点的依据
     * @param <AnyType>
     */
    private static class AVLNode<AnyType> extends TreeNode<AnyType>{

        int height; //节点高度
        AVLNode<AnyType> left;
        AVLNode<AnyType> right;

        public AVLNode(AnyType data,AVLNode left,AVLNode right){
            this(data,left,right,0);
        }

        public AVLNode(AnyType data){
            this(data,null,null);
        }

        public AVLNode(AnyType data,AVLNode left,AVLNode right,int height){
            super(data);
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }
}
