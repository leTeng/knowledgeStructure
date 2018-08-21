package com.eTeng.ds.heap.impl;

import com.eTeng.ds.heap.exception.UnderFlowException;
import com.eTeng.ds.heap.interfaces.MyHeap;

import java.util.Comparator;

/**
 * @FileName BinaryHeap.java
 * @Author 梁怡腾
 * @Date 2018/8/21
 * @Description 二叉堆的实现(数组)
 */
public class BinaryHeap<T extends Comparable<? super T>> implements MyHeap<T>{

    private static final int DEFAULT_HEAP_SIZE = 100;

    /**
     * 堆元素(其中数组索引为0的元素默认不作为堆元素处理,
     *          作为预处理元素的存储,堆元素真正开始位置为1)
     */
    private T [] elements;

    /**
     * 堆元素个数
     */
    private int currentSize;

    public BinaryHeap(){
        this(DEFAULT_HEAP_SIZE);
    }

    public BinaryHeap(int currentSize){
        allocationElements(currentSize);
        makeClrea();
    }

    public BinaryHeap(T[] items){
        this.currentSize = items.length;
        allocationElements((currentSize + 2) * 11 / 10);
        //堆元素从1开始
        int i = 1;
        for(T item : items){
            elements[i++] = item;
        }
        buildHeap();
    }

    public void insert(T t){

        if(currentSize == elements.length - 1){
            //扩容
            capacity(currentSize * 2 - 1);
        }
        int hole = ++currentSize;
        /**
         * 循环向上和父节点比较,如果节点优先级高于父节点,上滤到父节点。
         * 注意：索引为0的元素存储的是新添加元素。作为预处理元素。它的任务时终止循环,
         *      添加元素的优先级高于根元素,新添加元素上滤到根元素。那么最后与自身相比,
         *      终止循环。添加元素作为根元素。
         */
        for(elements[0]=t; t.compareTo(elements[hole / 2]) < 0; hole /= 2){
            elements[hole] = elements[hole/2];
        }
        elements[hole] = t;
    }

    public T removeMin(){
        if(isEmpty()){
            throw new UnderFlowException();
        }
        T element = elements[1];
        /**
         *  删除最高优先权元素,并将最后的一个元素代替最高优先权,
         *  然后根元素下滤。
         */

        elements[1] = elements[currentSize];
        elements[currentSize--] = null;
        percolateDown(1);
        return  element;
    }

    public boolean isEmpty(){
        return currentSize == 0;
    }

    public void doClear(){
        makeClrea();
    }

    public T findMin(){
        return elements[1];
    }

    public int size(){
        return currentSize;
    }

    private void capacity(int newSize){
        T[] oldElements = elements;
        allocationElements(newSize);
        for(int i = 0; i < oldElements.length; i++){
            elements[i] = oldElements[i];
        }
    }

    private void allocationElements(int newSize){
        elements = (T[])new Comparable[newSize];
    }

    /**
     * 构建堆,由最后节点的父节点开始下滤,向根节点的前进。总的上滤次数为堆
     * 所有节点的高度的和(树叶节点的高度为0)。
     */
    private void buildHeap(){
        for(int i = (currentSize / 2); i > 0; i--){
            percolateDown(i);
        }
    }

    private void makeClrea(){
        for(T element : elements){
            element = null;
        }
        currentSize = 0;
    }

    /**
     * 下滤
     */
    private void percolateDown(int hole){

        int child;
        T temp = elements[hole];
        for(;hole * 2 < currentSize;hole = child){
            child = hole * 2;
            //判断末节点父节点是否有两个子节点。并且右节点优先级高于左节点。
            if(child != currentSize && elements[child+1].
                    compareTo(elements[child]) < 0){
                child++;
            }
            if(elements[child].compareTo(temp) < 0){
                elements[hole] = elements[child];
            }else{
                break;
            }
        }
        elements[hole] = temp;
    }
}

