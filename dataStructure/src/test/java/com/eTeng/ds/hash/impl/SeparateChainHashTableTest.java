package com.eTeng.ds.hash.impl;

import org.junit.Before;
import org.junit.Test;

public class SeparateChainHashTableTest{

    private SeparateChainHashTable<Integer> separateChainHashTable;

    @Before
    public void setUp(){
        separateChainHashTable = new SeparateChainHashTable(14);
    }

    @Test
    public void cantains(){
        insert();
       System.out.println("is contains 1:"+ separateChainHashTable.cantains(1));
        System.out.println("is contains 6:"+separateChainHashTable.cantains(6));

    }

    @Test
    public void insert(){
        separateChainHashTable.insert(1);
        separateChainHashTable.insert(2);
        separateChainHashTable.insert(3);
    }

    @Test
    public void remove(){
        insert();
        separateChainHashTable.remove(1);
        System.out.println("is contains 1:"+separateChainHashTable.cantains(1));
        System.out.println(separateChainHashTable.size());
    }

    @Test
    public void get(){
        insert();
        System.out.println(separateChainHashTable.get(1));
        System.out.println(separateChainHashTable.get(5));
    }

    @Test
    public void makeEmpty(){
        separateChainHashTable.makeEmpty();
        System.out.println(separateChainHashTable.isEmpty());
    }

    @Test
    public void isEmpty(){
        insert();
        System.out.println(separateChainHashTable.isEmpty());
        remove();
        separateChainHashTable.remove(2);
        separateChainHashTable.remove(3);
        System.out.println(separateChainHashTable.isEmpty());
    }
}