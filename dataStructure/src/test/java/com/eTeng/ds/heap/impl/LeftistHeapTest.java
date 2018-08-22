package com.eTeng.ds.heap.impl;

import org.junit.Before;
import org.junit.Test;


public class LeftistHeapTest{

    private LeftistHeap heap1;
    private LeftistHeap heap2;

    @Before
    public void setUp() throws Exception{
        heap1 = new LeftistHeap();
        heap1.insert(3);
        heap1.insert(23);
        heap1.insert(14);
        heap1.insert(21);
        heap1.insert(8);
        heap1.insert(17);
        heap1.insert(10);
        heap1.insert(26);
        heap2 = new LeftistHeap();
        heap2.insert(24);
        heap2.insert(37);
        heap2.insert(18);
        heap2.insert(33);
        heap2.insert(18);
        heap2.insert(7);
        heap2.insert(6);
        heap2.insert(12);
    }

    @Test
    public void merge(){
        heap1.meger(heap2);
        System.out.println(heap1.size());
    }

    @Test
    public void insert(){
    }

    @Test
    public void removeMin(){
        System.out.println(heap1.removeMin());
        System.out.println(heap1.removeMin());
        System.out.println(heap1.findMin());
        System.out.println(heap2.removeMin());
        System.out.println(heap2.findMin());
    }

    @Test
    public void isEmpty(){
    }

    @Test
    public void doClear(){
        System.out.println("heap1 id empty? "+ heap1.isEmpty());
        heap1.doClear();
        System.out.println("heap1 id empty? "+ heap1.isEmpty());
    }

    @Test
    public void findMin(){
    }
}