package com.eTeng.ds.tree.binarytree.interfaces;

/**
 * @author eTeng
 *
 */
public interface BinarySearchTree<AnyType> {
	
	/**
	 * 清空ADT(二叉查找树)
	 */
	void makeEmpty();
	
	/**
	 * ADT是否为空(二叉查找树)
	 */
	
	boolean isEmpty();
	/**
	 * 是否包含某个元素
	 * @return 
	 */
	
	boolean contains(AnyType anyType);
	
	/**
	 * 查找ADT中最小元素(二叉查找树)
	 * @return
	 */
	
	AnyType findMin();
	/**
	 * 查找ADT中最大元素(二叉查找树)
	 * ADT 删除某个元素
	 * @return
	 */
	AnyType findMax();
	
	/**
	 * ADT 插入某个元素
	 */
	
	void insert(AnyType anyType);
	
	/**
	 * ADT 删除某个元素
	 */
	
	AnyType remove(AnyType anyType);
	
	/**
	 * 打印树(遍历树)
	 */
	
	void printTree();
}
