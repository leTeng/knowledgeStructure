package com.eTeng.ds.tree.binarytree.impl;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyBinarySearchTreeTest{

    private MyBinarySearchTree<Integer> myBinarySearchTree;

    @Before
    public void setUp(){
        myBinarySearchTree = new MyBinarySearchTree<Integer>();
    }

    @Test
    public void makeEmpty(){
        myBinarySearchTree.makeEmpty();
    }

    @Test
    public void isEmpty(){
        System.out.println(myBinarySearchTree.isEmpty());
        myBinarySearchTree.insert(0);
        System.out.println(myBinarySearchTree.isEmpty());
    }

    @Test
    public void contains(){
        myBinarySearchTree.insert(0);
        System.out.println(myBinarySearchTree.contains(0));
        System.out.println(myBinarySearchTree.contains(1));
    }

    @Test
    public void findMin(){
        myBinarySearchTree.insert(0);
        myBinarySearchTree.insert(4);
        myBinarySearchTree.insert(5);
        myBinarySearchTree.insert(22);
        myBinarySearchTree.insert(45);
        myBinarySearchTree.insert(-1);
        myBinarySearchTree.insert(8);
        System.out.println(myBinarySearchTree.findMin());

    }

    @Test
    public void findMax(){
        myBinarySearchTree.insert(0);
        myBinarySearchTree.insert(4);
        myBinarySearchTree.insert(5);
        myBinarySearchTree.insert(22);
        myBinarySearchTree.insert(45);
        myBinarySearchTree.insert(-1);
        myBinarySearchTree.insert(8);
        System.out.println(myBinarySearchTree.findMax());
    }

    @Test
    public void insert(){
        myBinarySearchTree.insert(0);
        myBinarySearchTree.insert(4);
        myBinarySearchTree.insert(5);
        myBinarySearchTree.insert(22);
        System.out.println(myBinarySearchTree.isEmpty());
    }

    @Test
    public void remove(){
        myBinarySearchTree.insert(5);
        myBinarySearchTree.insert(1);
        myBinarySearchTree.insert(2);
        myBinarySearchTree.insert(3);
        myBinarySearchTree.insert(4);
        myBinarySearchTree.insert(0);
        myBinarySearchTree.insert(6);
        myBinarySearchTree.insert(7);
        myBinarySearchTree.insert(8);
        System.out.println(myBinarySearchTree.remove(1));
    }

    @Test
    public void printTree(){
    }
}