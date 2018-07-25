package com.eTeng.ds.list.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Iterator;

public class MyLinkedListTest{

	private MyLinkedList<String> myLinkedList;
    @Before
    public void setUp() throws Exception{
    	myLinkedList = new MyLinkedList<String>();
    	myLinkedList.add("1");
    	myLinkedList.add("2");
    	myLinkedList.add("3");
    	System.out.println("初始化长度为：");
    	System.out.println(myLinkedList.size());
    	System.out.println(myLinkedList);
    }

    @Test
    public void add(){
    	myLinkedList.add("4");
    	System.out.println(myLinkedList.contain("4"));
    	System.out.println("节点最后添加： 4");
    	System.out.println(myLinkedList);
    	System.out.println(myLinkedList.size());
    }

    @Test
    public void add1(){
    	myLinkedList.add("4",2);
    	System.out.println(myLinkedList.contain("4"));
    	System.out.println("第 2 添加： 4");
    	System.out.println(myLinkedList);
    	System.out.println(myLinkedList.size());
    }

    @Test
    public void remove(){
    	myLinkedList.remove(2);
    	System.out.println(myLinkedList.contain("3"));
    	System.out.println("第 2 位删除：");
    	System.out.println(myLinkedList);
    	System.out.println(myLinkedList.size());
    }

    @Test
    public void remove1(){
    	myLinkedList.remove("3");
    	System.out.println(myLinkedList.contain("3"));
    	System.out.println("删除元素 :" + "\"3\"");
    	System.out.println(myLinkedList);
    	System.out.println(myLinkedList.size());
    }

    @Test
    public void size(){
    	System.out.println(myLinkedList.size());
    }

    @Test
    public void isEmpty(){
    	System.out.println(myLinkedList.isEmpty());
    }

    @Test
    public void clear(){
    	myLinkedList.clear();
    	System.out.println(myLinkedList.size());
    	System.out.println(myLinkedList);
    }

    @Test
    public void get(){
    	System.out.println("索引位 2 的值：");
    	System.out.println(myLinkedList.get(2));
    }

    @Test
    public void set(){
    	System.out.println("替換引位 2 的值為：2");
    	System.out.println(myLinkedList.set("2", 2));
    	System.out.println(myLinkedList);
    	
    }

    @Test
    public void contain(){
    	System.out.println(myLinkedList.contain("2"));
    }

    @Test
    public void indexof(){
    	System.out.println("index of");
    	System.out.println(myLinkedList.indexof("3"));
    }

    @Test
    public void iterator(){
    	Iterator<String> iterator = myLinkedList.iterator();
    	while(iterator.hasNext()) {
    		System.out.println(iterator.next());
    	}
    }

    @Test
    public void listIterator(){
    }

    @Test
    public void ensureCapacity(){
    }

    @Test
    public void trimToSize(){
    }
}