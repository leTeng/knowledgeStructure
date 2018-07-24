package com.eTeng.ds.list.interfaces;

import java.util.Iterator;
public interface MyCollection<AnyType>{


    /**
     * ADT元素个数
     */
    int size();

    /**
     * 是否为空
     */
    boolean isEmpty();

    /**
     * 清空
     */
    void clear();


    /**
     * 扩容
     */
    void ensureCapacity(int newCapacity);

    /**
     * 获取指定索引元素
     * @param i
     */
    AnyType get(int i);

    /**
     * 修改指定元素
     * @param anyType
     * @param i
     */
    AnyType set(AnyType anyType,int i);

    /**
     * 包含
     * @param anyType
     */
    boolean contain(AnyType anyType);

    /**
     * 添加
     * @param anyType
     * @return
     */
    boolean add(AnyType anyType);


    /**
     * 删除指定位置
     * @param index
     */
    void remove(int index);

    /**
     * 获取普通迭代器
     * @return
     */
    Iterator<AnyType> iterator();


}
