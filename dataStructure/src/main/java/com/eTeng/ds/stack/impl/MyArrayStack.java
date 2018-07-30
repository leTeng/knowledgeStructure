package com.eTeng.ds.stack.impl;

import com.eTeng.ds.stack.interfaces.MyStack;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/7/30
 * @Description
 */
public class MyArrayStack<T> implements MyStack<T>{

    /**
     * 默认栈的容器大小为 10
     */
    private int DEFAULT_CAPACITY = 10;

    /**
     * 栈
     */
    private Object [] elements;

    /**
     * 栈元素大小
     */
    private int size = 0;

    /**
     * 栈顶
     */
    int top = -1;

    public MyArrayStack(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayStack(int specifyCapacity){
        elements = new Object[specifyCapacity];
    }

    public void push(T t){
        if(elements.length < size()){
            throw new StackOverflowError();
        }
        elements[++top] = t;
        size++;
    }

    public int size(){
        return size;
    }

    public T pop(){
        if(top == -1){
            throw new IndexOutOfBoundsException();
        }
        T element = (T)elements[top];
        elements[top] = null;
        top--;
        return element;
    }

    public T top(){
    	if(top == -1) {
    		return null;
    	}
        return (T)elements[top];
    }
    

	@Override
	public void popAll() {
		while(top != -1) {
			pop();
		}
	}
	
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("[");
    	for(int idx = top; idx >= 0 ; idx--) {
    		sb.append(elements[idx]);
    		if(idx != 0) {
    			sb.append(",");
    		}
    	}
    	sb.append("]");
    	return sb.toString();
    }

}
