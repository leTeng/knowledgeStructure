package com.eTeng.ds.stack.practice;

import com.eTeng.ds.stack.impl.MyArrayStack;
import com.eTeng.ds.stack.interfaces.MyStack;
import org.junit.Before;
import org.junit.Test;

public class Practice_3_23_aTest{

    private static  String expression;
    private Practice_3_23_a practice_3_24;
    private MyStack<String> myStack;

    @Before
    public void setUp(){
        expression = "5 * ( 9 + 3 ) / ( 7 - 5 ) + 3";
        practice_3_24 = new Practice_3_23_a();
        myStack = new MyArrayStack<String>(20);
    }

    @Test
    public void convertSuffix(){
        String suffixExpression = practice_3_24.convertSuffix(expression,myStack);
        System.out.println(suffixExpression);
    }
}