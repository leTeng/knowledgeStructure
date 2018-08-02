package com.eTeng.ds.tree.binarytree.impl;

import org.junit.Before;
import org.junit.Test;

public class AVLBinaryTreeTest {

	private AVLBinaryTree<Integer> aVLBinaryTree;
	
	@Before
	public void setUp() {
		aVLBinaryTree = new AVLBinaryTree<>();
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
}
