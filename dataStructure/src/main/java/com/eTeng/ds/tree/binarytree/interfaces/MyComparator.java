package com.eTeng.ds.tree.binarytree.interfaces;

public interface MyComparator<AnyType> {

	/**
	 * 比较接口
	 * @param lhs 比较元素1
	 * @param rhs 比较元素2
	 * @return  0 相等  1 大于 -1 小于
	 */
	int compareTo(AnyType lhs , AnyType rhs);
}
