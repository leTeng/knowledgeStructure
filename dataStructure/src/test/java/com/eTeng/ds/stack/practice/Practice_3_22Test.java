package com.eTeng.ds.stack.practice;
import com.eTeng.ds.stack.impl.MyLinkedStack;
import com.eTeng.ds.stack.interfaces.MyStack;
import org.junit.Before;
import org.junit.Test;

public class Practice_3_22Test{

    static Practice_3_22 practice_3_22;
    static MyStack<String> myStack;
    static String suffixExpression;

    @Before
    public void setUp(){
        practice_3_22 = new Practice_3_22();
        myStack = new MyLinkedStack<String>();
        suffixExpression = "593+75-/*3+";
    }

    @Test
    public void compute(){
        double compute = practice_3_22.compute(myStack,suffixExpression);
        System.out.println(compute);
    }
}