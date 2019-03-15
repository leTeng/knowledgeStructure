package com.eTeng.ds.tree.Btree;

/**
 * @FileName BTree.java
 * @Author eTeng
 * @Date 2019/3/14 11:11
 * @Description B树接口
 */
public interface BTree<Key extends Comparable<? super Key>,Value>{

    int size();

    int height();

    boolean isEmpty();

    Value get(Key key);

    void add(Key key,Value val);

    void set(Key key,Value avl);

    void del(Key key);
}
