package com.eTeng.ds.hash.interfaces;

public interface MyHash<T>{

    boolean cantains(T t);

    void insert(T t);

    void remove(T t);

    T get(T t);

    void makeEmpty();

    boolean isEmpty();

    int size();
}
