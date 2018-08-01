package com.eTeng.ds.tree.binarytree;

import org.junit.Before;
import org.junit.Test;

public class ExpressionTreeTest{

    private String infixExpression;
    private ExpressionTree expressionTree;

    @Before
    public void setUp(){
        expressionTree = new ExpressionTree();
    }


    @Test
    public void testBuildExpreTree(){
        infixExpression = "( a + b ) * c - ( e - c ) * f";
        expressionTree.buildExpreTree(infixExpression);
    }
}