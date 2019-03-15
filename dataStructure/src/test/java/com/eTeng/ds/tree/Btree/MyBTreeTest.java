package com.eTeng.ds.tree.Btree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Random;

public class MyBTreeTest{

    private  MyBTree<Integer,String> myBTree;

    @Before
    public void setUp() throws Exception{
        myBTree = new MyBTree();
    }

    @Test
    public void size(){
        myBTree.add(1,"127.0.0.1");
        Assert.assertEquals(myBTree.size(),1);
    }

    @Test
    public void height(){
        myBTree.add(1,"127.0.0.1");
        Assert.assertEquals(myBTree.height(),0);
    }

    @Test
    public void isEmpty(){
        myBTree.add(1,"127.0.0.1");
        Assert.assertEquals(myBTree.isEmpty(),false);
    }

    @Test
    public void get(){
        add();
        String val = myBTree.get(6);
        Assert.assertEquals(val,"127.0.0.1");
    }

    @Test
    public void add(){
        myBTree.add(2,"127.0.0.2");
        myBTree.add(4,"127.0.0.4");
        myBTree.add(1,"127.0.0.1");
        myBTree.add(3,"127.0.0.3");
        myBTree.add(5,"127.0.0.5");
        myBTree.add(6,"127.0.0.6");
        myBTree.add(7,"127.0.0.7");
        myBTree.add(8,"127.0.0.8");
        myBTree.add(9,"127.0.0.9");
        myBTree.add(10,"127.0.0.10");
        myBTree.add(11,"127.0.0.11");
        myBTree.add(12,"127.0.0.12");
        myBTree.add(13,"127.0.0.13");
        myBTree.add(14,"127.0.0.14");
        myBTree.add(15,"127.0.0.15");
        myBTree.add(16,"127.0.0.16");
        myBTree.add(17,"127.0.0.17");
        myBTree.add(18,"127.0.0.18");
        myBTree.add(19,"127.0.0.19");
        myBTree.add(20,"127.0.0.20");
    }

    @Test
    public void pressureTest(){
        Random random = new Random();
        for(int i=1; i<10000000; i++){
            int key = random.nextInt(i);
            myBTree.add(key,"127.0.0." + key);
        }
        long start = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String s = myBTree.get(10);
        long end = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Assert.assertEquals(s,"127.0.0.10");
        long diff = end - start ;
        System.out.println(diff);
    }

    @Test
    public void set(){
    }

    @Test
    public void del(){
    }
}