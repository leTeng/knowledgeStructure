package com.eTeng.ds.tree.practice.impl;
import java.util.Collection;
import java.util.Iterator;

public interface MyTreeSet<AnyType>{

    /**
     * 删除元素
     * @param anyType
     */
    void remove(AnyType anyType);


    /**
     * 删除多个元素
     */
    void removeAll(Collection<AnyType> collection);

    /**
     * 添加元素（添加重复的元素进行更新操作）
     * @param anyType
     */
    void add(AnyType anyType);

    /**
     * ADT 的元素个数
     * @return
     */
    int size();

    /**
     * 添加多个元素
     * @param collection
     */
    void addAll(Collection<AnyType> collection);

    /**
     * 是否包含元素
     * @param anyType
     * @return
     */
    boolean contians(AnyType anyType);

    /**
     * 更新元素
     * @param anyType
     */
    void set(AnyType anyType);

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 清空集合
     */
    void makeEmpty();

    /**
     * 查找最小
     * @return
     */

    AnyType findMin();

    /**
     * 查找最大
     * @return
     */

    AnyType findMax();

    /**
     * 查找指定位置元素
     * @param index 位置
     * @return
     */
    AnyType get(int index);

    /**
     * 获取迭代器
     * @return
     */
    Iterator<AnyType> iterator();
}
