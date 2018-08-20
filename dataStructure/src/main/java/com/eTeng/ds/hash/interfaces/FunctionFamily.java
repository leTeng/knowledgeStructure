package com.eTeng.ds.hash.interfaces;

public interface FunctionFamily<T>{

    void generatorNewFunction();

    int hash(T t , int witch);

    int functionNum();
}
