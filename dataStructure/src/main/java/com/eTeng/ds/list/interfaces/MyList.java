package com.eTeng.ds.list.interfaces;
import java.util.ListIterator;

public interface MyList<AnyType> extends MyCollection<AnyType>{


    /**
     * 获取 List 特定迭代器
     */
    ListIterator<AnyType> listIterator();

    /**
     * 指定位置添加
     */
    void add(AnyType anyType , int index);

    /**
     * 删除最后
     * @param anyType
     * @return
     */
    AnyType remove(AnyType anyType);

    /**
     * 窄化ADT容量为当前元素大小
     */
    void trimToSize();

    /**
     * 批量添加
     * @param myList
     */
    void addAll(MyList<AnyType> myList);

    /**
     * 批量删除
     * @param myList
     */
    void removeAll(MyList<AnyType> myList);
}
