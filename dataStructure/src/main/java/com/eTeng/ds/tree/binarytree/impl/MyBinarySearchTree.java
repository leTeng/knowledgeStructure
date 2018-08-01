package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.BinarySearchTree;
import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;
import com.sun.xml.internal.bind.AnyTypeAdapter;

public class MyBinarySearchTree<AnyType> implements BinarySearchTree<AnyType> {

	/**
	 * 根节点
	 */
	private Node<AnyType> root;
	
	private MyComparator myComparator;
	@Override
	public void makeEmpty() {
		root = null;
	}

	public MyBinarySearchTree() {
		this(null);
	}
	
	/**
	 * 提供比较器初始化
	 * @param myComparator
	 */
	public MyBinarySearchTree(MyComparator<AnyType> myComparator) {
		this.myComparator = myComparator;
	}
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean contains(AnyType anyType) {
		return makeContains(anyType,root);
	}

	@Override
	public AnyType findMin() {
		return makefindMin(root);
	}

	@Override
	public AnyType findMax() {
		return makefindMax(root);
	}
	
	@Override
	public void insert(AnyType anyType) {
		makeInster(anyType,root);	
	}

	@Override
	public void remove(AnyType anyType) {
		makeRemove(anyType,root);
	}


	@Override
	public void printTree() {
		makePrint(root);
	}

	private boolean makeContains(AnyType AnyType,Node<AnyType> node) {
		
		if(node == null) {
			return false;
		}
		compareTo(AnyType);
		if()
		// TODO Auto-generated method stub
		return false;
	}


	private AnyType makefindMin(Node<AnyType> node) {
		// TODO Auto-generated method stub
		return null;
	}

	private AnyType makefindMax(Node<AnyType> node) {
		// TODO Auto-generated method stub
		return null;
	}

	private void makeInster(AnyType anyType, Node<AnyType> node) {
		// TODO Auto-generated method stub
		
	}

	private void makeRemove(AnyType anyType, Node<AnyType> node) {
		// TODO Auto-generated method stub
		
	}

	private void makePrint(Node<AnyType> node) {
		// TODO Auto-generated method stub
		
	}

	private int compareTo(AnyType target) {
		
		//提供自定义比较器
		if(myComparator != null) {
			return myComparator.compareTo(rhs);	
		}else {
			if(lhs instanceof Comparable && 
					rhs instanceof Comparable) {
				Comparable<AnyType> cpa = (Comparable<AnyType>)lhs;
				return cpa.compareTo(rhs);
			}else {
				throw new ClassCastException("The type of compare must implement the Comparable");
			}
		}
	}
	
	private static class Node<AnyType>{
		
		AnyType data; //元素数据
		Node<AnyType> left; //左子树
		Node<AnyType> right; //右子树
		
		public Node(AnyType data) {
			this(data,null,null);
		}
		
		public Node(AnyType data,Node<AnyType> left ,
				Node<AnyType> right) {
				this.data = data;
				this.left = left;
				this.right = right;
		}
	}
}
