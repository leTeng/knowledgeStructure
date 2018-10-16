package com.eTeng.ds.stack.impl;


import com.eTeng.ds.stack.interfaces.MyStack;

/**
 * @FileName
 * @Author eTeng
 * @Date 2018/7/30
 * @Description
 */
public class MyLinkedStack<T> implements MyStack<T>{

	/**
	  * 栈顶
	 */
	private Node<T> top;
	
	/**
	 * 栈元素个数
	 */
	private int size;
	
	public void push(T t) {
		Node<T> prev;
		if(top == null) {
			prev = null;
		}else {
			prev = top;
		}
		top = new Node<T>(t, prev);
		size++;
		
	}

	public T pop() {
		if(top == null) {
			throw new IllegalStateException();
		}
		Node<T> popNode = top;
		T data = (T)popNode.data;
		top = top.prev;
		popNode = null;
		size--;
		return data;
	}

	public T top() {
		if(top == null) {
			return null;
		}
		return top.data;
	}
	

	public void popAll() {
		while(top != null) {
			pop();
		}
	}

	public int size(){
		return size;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node<T> current = top;
		sb.append("[");
		while(current != null) {
			sb.append(current.data);
			current = current.prev;
			if(current != null) {
				sb.append(",");	
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	private class Node<T>{
		public T data;
		public Node<T> prev;
		
		public Node(T data , Node<T> prev) {
			this.data = data;
			this.prev = prev;
		}
 	}

}
