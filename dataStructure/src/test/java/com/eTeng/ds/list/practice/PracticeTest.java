package com.eTeng.ds.list.practice;

import com.eTeng.ds.list.impl.MyLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PracticeTest{

    private MyLinkedList<Integer> myLinkedList;
    private MyLinkedList<Integer> myLinkedList2;

    @Before
    public void setUp() throws Exception{
        myLinkedList = new MyLinkedList();
        myLinkedList.add(2);
        myLinkedList.add(3);
        myLinkedList.add(4);
        myLinkedList.add(5);
        System.out.println(myLinkedList);

        myLinkedList2 = new MyLinkedList();
        myLinkedList2.add(0);
        myLinkedList2.add(1);
        System.out.println(myLinkedList2);
    }

    @Test
    public void splice(){
        Practice<Integer> practice = new Practice<Integer>();
        practice.splice(myLinkedList2.listIterator(),myLinkedList);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList2);
    }
}