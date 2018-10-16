package com.eTeng.ds.heap.impl;

import com.eTeng.ds.heap.interfaces.MyHeap;

/**
 * @FileName BinomialQueue.java
 * @Author eTeng
 * @Date 2018/8/23
 * @Description
 */
public class BinomialQueue<T extends Comparable<? super T>>
        implements MyHeap<T>{

    /**
     * 二项式数组(多个二项式树组成一个队列)
     */
    private Node[] trees;

    /**
     * 元素个数
     */
    private int currentSize;


    public void insert(T t){

    }

    public T removeMin(){
        return null;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void doClear(){

    }

    public Node<T> merge(Node<T> tree1){
        return null;
    }

    public T findMin(){
        return null;
    }

    /**
     * 二项队列节点
     * @param <T> 节点数据
     */
    private static class Node<T>{

        private T data;
        private Node<T> left;
        private Node<T> nextSibling;

        public Node(T data){
            this(data,null,null);
        }

        public Node(T data,Node<T> left,Node<T> nextSibling){
            this.data = data;
            this.left = left;
            this.nextSibling = nextSibling;
        }
    }
}
