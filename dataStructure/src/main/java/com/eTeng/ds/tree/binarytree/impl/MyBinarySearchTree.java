package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.BinarySearchTree;
import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;

public class MyBinarySearchTree<AnyType> implements BinarySearchTree<AnyType>{

	/**
	 * 根节点
	 */
	private TreeNode<AnyType> root;
	
	private MyComparator myComparator;

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

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public boolean contains(AnyType anyType) {
		return makeContains(anyType,root);
	}

	public AnyType findMin() {
		return makefindMin(root);
	}

	public AnyType findMax() {
		return makefindMax(root);
	}
	
	public void insert(AnyType anyType) {
		makeInsert(anyType,root);
	}

	public AnyType remove(AnyType anyType) {
		TreeNode<AnyType> node = makeRemove(anyType,root);
		if(node != null){
			return node.data;
		}else{
			return null;
		}
	}


	public void printTree() {
		makePrint(root);
	}

	private boolean makeContains(AnyType anyType , TreeNode<AnyType> node) {

		if(node == null) {
			 return false;
		}
		int result = compareTo(node.data,anyType);
		if(result < 0){
			return makeContains(anyType,root.left);
		}else if(result > 0){
			return makeContains(anyType,root.right);
		}else{
			return true;
		}
	}


	private AnyType makefindMin(TreeNode<AnyType> node) {

		if(node == null){
			return null;
		}
		//循环实现
		/*while(node.left != null){
			node = node.left;
		}
		data = node.data;*/

		//递归实现
		if(node.left == null){
			return node.data;
		}else{
			return makefindMin(node.left);
		}
	}

	private AnyType makefindMax(TreeNode<AnyType> node) {
		if(node == null){
			 return  null;
		}

		//循环实现
		/*while(node.left != null){
			node  = node.left;
		}
		return node.data;*/

		//递归实现
		if(node.right != null){
			return makefindMax(node.right);
		}else{
			return node.data;
		}
	}

	/**
	 * 插入算法：
	 * 		1.依靠Comparator接口的compareTo()比较两个元素。比当前元素小，递归左节点再比较，
	 * 		如果大于当前元素，递归有节点在比较。如果当前元素为空，分配空间（创建节点）。将创建
	 * 		的节点连接到上一个节点（通过递归返回）。
	 * @param anyType
	 * @param node
	 * @return 返回值是最新的根节点
	 */
	private TreeNode<AnyType> makeInsert(AnyType anyType, TreeNode<AnyType> node) {

		if(node == null){
			return new Node<AnyType>(anyType,null,null);
		}

		int result = compareTo(node.data,anyType);
		if(result < 0){
			node.left = makeInsert(anyType,node.left);
		}else if(result > 0){
			node.right = makeInsert(anyType,node.right);
		}else{
			//等于情况的操作,执行更新动作
			node.data = anyType;
		}
		return node;
	}

	/**
	 * 删除算法:
	 * 		  存在两种情况：
	 * 		  				1.需要删除的节点只有一个子节点：让父节点直接绕过需要删除的节点，
	 * 		  				然后直接连接需要删除的子节点
	 *
     * 		  				2.需要删除的节点有两个子节点：使用需要删除节点的右节点的最小节点或者左节点的最大节点替换
     * 		  				  需要删除的节点。然后删除替换的节点。(删除替换的节点是第一种情况，因为是最小的节点，
     * 		  				  所以其最有只有一个节点)
	 *
	 * @param anyType
	 * @param node
	 */
	private TreeNode<AnyType> makeRemove(AnyType anyType, TreeNode<AnyType> node) {

		if(node == null){
			return node;
		}

		int result = compareTo(node.data,anyType);
		if(result < 0){
			node.left = makeRemove(anyType,node.left);
		}else if(result > 0){
			node.right = makeRemove(anyType,node.right);
		}else if(node.left != null && node.right != null){
			node.data = makefindMin(node.right);
			node.right = makeRemove(node.data,node.right);
		}else{
			 node = (node.left == null) ? node.right : node.left;
		}
		return node;
	}

	private void makePrint(TreeNode<AnyType> node) {
		// TODO Auto-generated method stub
		
	}

	protected int compareTo(AnyType compare , AnyType beCompare) {
		
		//提供自定义比较器
		if(myComparator != null) {
			return myComparator.compareTo(compare,beCompare);
		}else {
			if(compare instanceof Comparable &&
					beCompare instanceof Comparable) {
				Comparable<AnyType> cpa = (Comparable<AnyType>)compare;
				return cpa.compareTo(beCompare);
			}else {
				throw new ClassCastException("The type of compare must implement the Comparable");
			}
		}
	}
	
	private static class Node<AnyType> extends TreeNode<AnyType>{

		public Node(AnyType anyType , TreeNode<AnyType> left,
						 TreeNode<AnyType> right){
			super(anyType,left,right);
		}
	}
}
