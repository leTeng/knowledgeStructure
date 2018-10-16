package com.eTeng.ds.heap.impl;

import com.eTeng.ds.heap.exception.UnderFlowException;
import com.eTeng.ds.heap.interfaces.MyHeap;

/**
 * @FileName LeftistHeap.java
 * @Author eTeng
 * @Date 2018/8/22
 * @Description 左式堆(可支持合并的二叉堆),所有操作依靠合并完成。
 */
public class LeftistHeap<T extends Comparable<? super T>> implements MyHeap<T>{

    /*
     * 合并算法：时间复杂度O(log n)
     *          1.将根节点较小的堆的右子树与根节点较大的堆递归进行合并。
     *              (1)新根节点小于另一个堆的新根节点,将根节点小的新根节点切换为右节点。
     *              (2)再和另一个堆的新根节点比较。
     *              (3)如果还小于,继续(1)、(2)。
     *              (4)直到小的新根节点大于另一个堆(大的)的新根节点,将两个新根节点调换后,重复(1)、(2)。
     *
     *          2. (1)直到小的一方的新根节点没有右节点位置,将另一个堆((大的))的新根节点置为小的一方的新根节点的右节点。
     *             (2)并且判断小的一方新根节点有没有破坏左式树的性质,如果有调换左右子树。
     *             (3)并且更新小的一方新根节点npl(右子树npl + 1).
     *             (4)沿着递归的路径对每个新根节点重复(1)、(2)、(3)。
     *
     *
     */



    /**
     * 根节点
     */
    private Node<T> root;

    /**
     * 堆元素个数
     */
    private int currentSize;

    /**
     * 构造器
     */
    public LeftistHeap(){
        clear();
    }

    public void insert(T t){
        root = merge(new Node<T>(t),root);
        currentSize++;
    }

    /**
     * 删除最小元素(合并左右子树得出新根节点的堆)
     * @return
     */
    public T removeMin(){
        if(root == null){
            throw new UnderFlowException();
        }
        T data = root.data;
        root = merge(root.left,root.right);
        currentSize--;
        return data;
    }

    public void meger(LeftistHeap<T> rhs){
        merge(root,rhs.root);
        currentSize += rhs.currentSize;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void doClear(){
        clear();
    }

    public T findMin(){
        if(root == null){
            throw new UnderFlowException();
        }
        return root.data;
    }

    private Node<T> merge(Node<T> h1 , Node<T> h2){

        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        //优先级高(小)的节点右子树和另外的子树递归合并
        if(h1.data.compareTo(h2.data) < 0){
           return merge1(h1,h2);
        }else{
            return merge1(h2,h1);
        }
    }

    public int size(){
        return currentSize;
    }
    private Node<T> merge1(Node<T> h1 , Node<T> h2){

        /**
         * 处理只有一个节点的子树(新添加的节点,
         *     并不是合并子树的节点,因为一颗左式树不可能没有左子树)
         */
        if(h1.left == null){
            h1.left = h2;
        }else{
            //递归合并右子树
            h1.right = merge(h1.right,h2);
            //破坏左右子树npl,交换左右子树
            if(h1.left.npl < h1.right.npl){
                swapChildreb(h1);
            }
            //更新npl,子树的npl永远等于右节点的npl + 1
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

    private void clear(){
        root = null;
        currentSize = 0;
    }

    /**
     * 交换左右子树
     * @param h1
     */
    private void swapChildreb(Node<T> h1){
        Node<T> temp = h1.left;
        h1.left = h1.right;
        h1.right = temp;
    }

    private static class Node<T>{

        T data; //节点数据
        Node<T> left; //左节点
        Node<T> right; //右节点
        int npl; //零路径长(维护左式堆性质的依据)

        public Node(T data){
            this(data,null,null,0);
        }

        public Node(T data,Node<T> left,Node<T> right,int npl){
            this.data = data;
            this.left = left;
            this.right = right;
            this.npl = npl;
        }
    }
}
