package com.eTeng.ds.hash.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadraticProbingHashTableTest{

    private QuadraticProbingHashTable<Integer> quadraticProbingHashTable;

    @Before
    public void setUp() throws Exception{
        quadraticProbingHashTable = new QuadraticProbingHashTable(4);
    }

    @Test
    public void cantains(){
        insert();
        System.out.println("is contains 1: "+
                quadraticProbingHashTable.cantains(1));
        System.out.println("is contains 10: "+
                quadraticProbingHashTable.cantains(10));
    }

    @Test
    public void insert(){
        quadraticProbingHashTable.insert(1);
        quadraticProbingHashTable.insert(2);
        quadraticProbingHashTable.insert(3);
        quadraticProbingHashTable.insert(4);
    }

    @Test
    public void remove(){
        insert();
        System.out.println(quadraticProbingHashTable.size());
        quadraticProbingHashTable.remove(1);
        quadraticProbingHashTable.remove(2);
        System.out.println(quadraticProbingHashTable.get(1));
        System.out.println(quadraticProbingHashTable.size());
    }

    @Test
    public void get(){
        insert();
        System.out.println(quadraticProbingHashTable.get(1));
        System.out.println(quadraticProbingHashTable.get(6));
    }

    @Test
    public void makeEmpty(){
        insert();
        System.out.println(quadraticProbingHashTable.size());
        quadraticProbingHashTable.makeEmpty();
        System.out.println(quadraticProbingHashTable.size());
    }

    @Test
    public void isEmpty(){
        System.out.println(quadraticProbingHashTable.isEmpty());
        insert();
        System.out.println(quadraticProbingHashTable.isEmpty());
    }

    @Test
    public void size(){
        insert();
        System.out.println(quadraticProbingHashTable.size());
    }
}