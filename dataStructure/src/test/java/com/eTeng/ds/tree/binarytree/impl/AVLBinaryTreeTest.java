package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class AVLBinaryTreeTest {

	private AVLBinaryTree<Integer> aVLBinaryTree;
	
	@Before
	public void setUp() {
		aVLBinaryTree = new AVLBinaryTree<Integer>(new MyComparator(){
			public int compareTo(Object lhs,Object rhs){
				if(lhs instanceof Integer && rhs instanceof Integer){
					Integer beCompare = (Integer) lhs;
					Integer compare = (Integer) rhs;
					if(beCompare < compare){
						return 1;
					}else if(beCompare > compare){
						return -1;
					}else{
						return 0;
					}
				}
				return -1;
			}
		});
	}
	
	@Test
	public void isEmpty() {
		System.out.println(aVLBinaryTree.isEmpty());
		aVLBinaryTree.insert(0);
		System.out.println(aVLBinaryTree.isEmpty());
	}
	
	@Test
	public void findMin() {
		aVLBinaryTree.insert(0);
		aVLBinaryTree.insert(1);
		aVLBinaryTree.insert(2);
		aVLBinaryTree.insert(3);
		aVLBinaryTree.insert(4);
		aVLBinaryTree.insert(5);
		aVLBinaryTree.insert(6);
		System.out.println(aVLBinaryTree.findMin());
	}
	
	@Test
	public void findMax() {
		aVLBinaryTree.insert(0);
		aVLBinaryTree.insert(1);
		aVLBinaryTree.insert(2);
		aVLBinaryTree.insert(3);
		aVLBinaryTree.insert(4);
		aVLBinaryTree.insert(5);
		aVLBinaryTree.insert(6);
		System.out.println(aVLBinaryTree.findMax());
	}
	
	@Test
	public void contains() {
		aVLBinaryTree.insert(0);
		aVLBinaryTree.insert(1);
		aVLBinaryTree.insert(2);
		aVLBinaryTree.insert(3);
		aVLBinaryTree.insert(4);
		aVLBinaryTree.insert(5);
		aVLBinaryTree.insert(6);
		System.out.println(aVLBinaryTree.contains(4));
	}
	
	@Test
	public void insert() {
		aVLBinaryTree.insert(0);
		aVLBinaryTree.insert(1);
		aVLBinaryTree.insert(2);
		aVLBinaryTree.insert(3);
		aVLBinaryTree.insert(4);
		aVLBinaryTree.insert(5);
		aVLBinaryTree.insert(7);
		aVLBinaryTree.insert(8);
		aVLBinaryTree.insert(9);
		aVLBinaryTree.insert(10);
		aVLBinaryTree.insert(11);
		aVLBinaryTree.insert(12);
	}

	@Test
	public void remove(){
		aVLBinaryTree.insert(0);
		aVLBinaryTree.insert(1);
		aVLBinaryTree.insert(2);
		aVLBinaryTree.insert(3);
		aVLBinaryTree.insert(4);
		aVLBinaryTree.insert(5);
		aVLBinaryTree.insert(7);
		aVLBinaryTree.remove(4);
		aVLBinaryTree.remove(5);
		aVLBinaryTree.remove(7);
	}

	@Test
	public void print(){
		Random ran = new Random(100);
		for(int i = 1; i<25; i++){
			aVLBinaryTree.insert(i);
		}
//		aVLBinaryTree.printTree();
		aVLBinaryTree.printTreeMore(MyBinarySearchTree.PrintForm.POST);
		aVLBinaryTree.printTreeMore(MyBinarySearchTree.PrintForm.PRE);
	}
}
