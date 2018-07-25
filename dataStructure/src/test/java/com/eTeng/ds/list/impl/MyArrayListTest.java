package com.eTeng.ds.list.impl;

import org.junit.Before;

import java.util.Iterator;

public class MyArrayListTest{

    private static  MyArrayList<Integer> myArrayList;

    @Before
    public void testBrfore(){
        myArrayList  = new MyArrayList<Integer>();
        myArrayList.add(0);
        myArrayList.add(1);
        myArrayList.add(3);
        System.out.println(myArrayList.size());
        System.out.println("初始化序列: \n");
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void trimToSize(){

    }

    @org.junit.Test
    public void get(){
        System.out.println(myArrayList.get(1));
    }

    @org.junit.Test
    public void size(){
        System.out.println(myArrayList.size());
    }

    @org.junit.Test
    public void isEmpty(){
        System.out.println(myArrayList.isEmpty());
    }

    @org.junit.Test
    public void clear(){
        myArrayList.clear();
        System.out.println(myArrayList.size());
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void add(){
        myArrayList.add(5,2);
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void add1(){
        myArrayList.add(5);
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void remove(){
        myArrayList.remove(3);
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void remove1(){
        myArrayList.remove((Integer) 2);
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void set(){
        myArrayList.set(1,2);
        System.out.println(myArrayList);
    }

    @org.junit.Test
    public void contain(){
        System.out.println(myArrayList.contain(1));
    }

    @org.junit.Test
    public void ensureCapacity(){
    }

    @org.junit.Test
    public void iterator(){
        Iterator<Integer> iterator = myArrayList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @org.junit.Test
    public void listIterator(){
    }
}