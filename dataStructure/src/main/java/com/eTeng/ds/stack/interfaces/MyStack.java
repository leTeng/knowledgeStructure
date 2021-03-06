package com.eTeng.ds.stack.interfaces;

/**
 * @FileName
 * @Author eTeng
 * @Date 2018/7/30
 * @Description
 */
public interface MyStack<T>{

    /**
     * 入栈(压栈)
     */
    void push(T t);

    /**
     * 出栈(弹栈)
     * @return
     */
    T pop();

    /**
     * 栈顶
     * @return
     */
    T top();
    
    /**
     * 全部出栈
     */
    void popAll();

    /**
     * 栈的长度
     */
    int size();
}
