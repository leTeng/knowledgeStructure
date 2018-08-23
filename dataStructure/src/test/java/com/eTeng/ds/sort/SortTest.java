package com.eTeng.ds.sort;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.Random;

import static org.junit.Assert.*;

public class SortTest{

    private Integer [] arr;
    private Sort<Integer> sort;
    private Random random;
    @Before
    public void setUp() throws Exception{
        sort = new Sort<Integer>();
        random = new Random();
        arr = new Integer[1000000];
//        arr = new Integer[]{1,3,54,64,23,7,2,24,652,234,6,44};
        for(int i=0 ; i < 100000; i++){
            arr[i] = random.nextInt(1000000);
        }
    }

    @Test
    public void insertSort(){

//        System.out.println(Arrays.toString(arr));
        long start = new Date().getTime();
        System.out.println();
        sort.insertSort(arr);
//        System.out.println(Arrays.toString(arr));
        long end = new Date().getTime();
        System.out.println((end - start));
    }

    @Test
    public void shellSort(){
//        System.out.println(Arrays.toString(arr));
        long start = new Date().getTime();
        sort.shellSort(arr);
//        System.out.println(Arrays.toString(arr));
        long end = new Date().getTime();
        System.out.println((end - start));
    }
}