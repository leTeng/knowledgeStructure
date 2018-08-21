package com.eTeng.ds.heap.interfaces;

public interface MyHeap<T>{

    /**
     * 入队(按优先级排序)
     * @param t
     */
    void insert(T t);

    /**
     * 出队(优先级最高的)
     */
    T removeMin();

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void doClear();

    /**
     *下一个出队元素(优先级最高的)
     */
    T findMin();
}
