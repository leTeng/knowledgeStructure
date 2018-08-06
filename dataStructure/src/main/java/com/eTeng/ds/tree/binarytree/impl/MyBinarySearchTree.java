package com.eTeng.ds.tree.binarytree.impl;

import com.eTeng.ds.tree.binarytree.interfaces.BinarySearchTree;
import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;

public class MyBinarySearchTree<AnyType> implements BinarySearchTree<AnyType>{

	/**
	 * 根节点
	 */
	protected TreeNode<AnyType> root;
	
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
		root = makeInsert(anyType,root);
	}

	public AnyType remove(AnyType anyType) {
		TreeNode<AnyType> node = makeRemove(anyType,root);
		if(node != null){
			return node.data;
		}else{
			return null;
		}
	}

	/**
	 * 默认使用中序遍历
	 */
	public void printTree(){
		printTreeMore(PrintForm.MID);
	}

	/**
	 * 提供多种遍历方式（前中后）
	 * @param printForm
	 */
	public void printTreeMore(PrintForm printForm) {
		if(isEmpty()){
			System.out.println("is empty tree");
			return;
		}
		switch(printForm){
			case MID:
				makePrintMid(root);
				break;
			case PRE:
				makePrintPre(root);
				break;
			case POST:
				makePrintPost(root);
				break;
		}
	}


	public int height(){
		return height(root);
	}

	protected boolean makeContains(AnyType anyType , TreeNode<AnyType> node) {

		if(node == null) {
			 return false;
		}
		int result = compareTo(node.data,anyType);
		if(result < 0){
			return makeContains(anyType,node.left);
		}else if(result > 0){
			return makeContains(anyType,node.right);
		}else{
			return true;
		}
	}


	protected AnyType makefindMin(TreeNode<AnyType> node) {

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

	protected AnyType makefindMax(TreeNode<AnyType> node) {
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

	/**
	 *	遍历树 (中序)
	 *  算法：
	 *  	1.首先根节点遍历到最左边节点，然后如果节点有右子树。以当前节点为根节点，遍历到最边元素。
	 *      2.遍历到最左边元素后，按照遍历的向上路径弹栈。
	 * @param node
	 */
	private void makePrintMid(TreeNode<AnyType> node) {

		if(node != null){
			//當前根节点沿着最左边前进
			makePrintMid(node.left);
			//到达基准情况后，递归弹栈。输出数据
			System.out.println(node.data);
			//如果节点有右节点重复沿着当前节点最左边方向前进
			makePrintMid(node.right);
		}
	}

	/**
	 * 遍历树(前序)
	 * @param node
	 */
	private void makePrintPre(TreeNode<AnyType> node){

		if(node != null){
			System.out.println(node.data);
			makePrintPre(node.left);
			makePrintPre(node.right);
		}
	}

	/**
	 * 遍历树(后序)
	 * @param node
	 */
	private void makePrintPost(TreeNode<AnyType> node){
		if(node != null){
			makePrintPost(node.left);
			makePrintPost(node.right);
			System.out.println(node.data);
		}
	}

	protected int compareTo(AnyType beCompare , AnyType compare) {
		
		//提供自定义比较器
		if(myComparator != null) {
			return myComparator.compareTo(beCompare,compare);
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

	/**
	 * 获取高度(通过后序遍历树)
	 * @return
	 */
	private int height(TreeNode<AnyType> node){
		if(node == null){
			return -1;
		}
		return 1 + Math.max(height(node.left),height(node.right));
	}

	private static class Node<AnyType> extends TreeNode<AnyType>{

		public Node(AnyType anyType , TreeNode<AnyType> left,
						 TreeNode<AnyType> right){
			super(anyType,left,right);
		}
	}

	public enum  PrintForm{
		PRE, //前序
		POST, //后序
		MID  //中序
	}
}
