package com.eTeng.ds.stack.practice;

import com.eTeng.ds.list.impl.MyLinkedList;
import com.eTeng.ds.list.interfaces.MyList;
import com.eTeng.ds.stack.impl.MyLinkedStack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PracticeTest{

    private Practice practice;

    @Before
    public void setUp(){
        practice = new Practice();
    }
    @Test
    public void checkPascalSymbol(){
        MyList myList = new MyLinkedList();
//        myList.add("[");
//        myList.add("(");
        myList.add("begin");
        myList.add("[");
        myList.add("void");
        myList.add("[");
        myList.add("]");
        myList.add("{");
        myList.add("test");
        myList.add("}");
        myList.add("]");
        myList.add("/end");
        practice.checkPascalSymbol(myList,new MyLinkedStack<String>());
    }

    @Test
    public void checkJavaSymbol(){
        MyList myList = new MyLinkedList();
        myList.add("/*");
        myList.add("[");
        myList.add("void");
        myList.add("[");
        myList.add("]");
        myList.add("{");
        myList.add("test");
        myList.add("}");
        myList.add("]");
        myList.add("*/");
        practice.checkJavaSymbol(myList,new MyLinkedStack<String>());
    }
}