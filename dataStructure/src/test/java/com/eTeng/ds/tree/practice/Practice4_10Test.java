package com.eTeng.ds.tree.practice;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Practice4_10Test{

    private Practice4_10 practice;

    @Before
    public void setUp(){
        practice = new Practice4_10();
    }

    @Test
    public void printFiles(){
        practice.printFiles("G:\\java7",0);
    }
}