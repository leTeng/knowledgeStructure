package com.eTeng.ds.heap.impl;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BinaryHeapTest{

    private BinaryHeap<Integer> binaryHeap;

    @Before
    public void setUp() throws Exception{
      /*  Integer [] ints = {1,3,4,5,2,11,3,7,19,4};
        binaryHeap = new BinaryHeap<Integer>(ints);*/
        binaryHeap = new BinaryHeap<Integer>();
    }

    @Test
    public void insert(){
        System.out.println(binaryHeap.size());
        binaryHeap.insert(10);
        binaryHeap.insert(12);
        System.out.println(binaryHeap.size());

    }

    @Test
    public void removeMin(){
        insert();
        System.out.println(binaryHeap.size());
        System.out.println(binaryHeap.removeMin());
        System.out.println(binaryHeap.removeMin());
        System.out.println(binaryHeap.removeMin());
        System.out.println(binaryHeap.size());
    }

    @Test
    public void isEmpty(){
        System.out.println("heap is empry: "+binaryHeap.isEmpty());
        insert();
        System.out.println("heap is empry: "+binaryHeap.isEmpty());
    }

    @Test
    public void doClear(){
        insert();
        System.out.println("heap is empry: "+binaryHeap.isEmpty());
        binaryHeap.doClear();
        System.out.println("heap is empry: "+binaryHeap.isEmpty());
    }

    @Test
    public void findMin(){
        insert();
        System.out.println("heap min is: "+binaryHeap.findMin());
    }
}