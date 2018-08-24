package com.eTeng.ds.sort;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class SortTest{

    private Integer [] arr;
    private Sort<Integer> sort;
    private Random random;
    @Before
    public void setUp() throws Exception{
        sort = new Sort<Integer>();
        random = new Random();
        arr = new Integer[20000000];
//        arr = new Integer[]{1,3,54,64,23,7,2,24,652,234,6,44};
        for(int i=0 ; i < 20000000; i++){
            arr[i] = random.nextInt(20000000);
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

    @Test
    public void headSort(){

        System.out.println(Arrays.toString(arr));
//        long start = new Date().getTime();
        sort.headSort(arr);
        System.out.println(Arrays.toString(arr));
//        long end = new Date().getTime();
//        System.out.println((end - start));
    }


    @Test
    public void mergeSort(){
                long start = new Date().getTime();
        sort.mergeSort(arr);
//        System.out.println(Arrays.toString(arr));
        long end = new Date().getTime();
        System.out.println((end - start));
    }

    @Test
    public void sortPerformance(){
        long start = new Date().getTime();

        sort.insertSort(arr);
        long end01 = new Date().getTime();
        System.out.println("insert: " +(end01 - start));

        sort.shellSort(arr);
        long end02 = new Date().getTime();
        System.out.println("shell: "+(end02 - end01));

        sort.headSort(arr);
        long end03 = new Date().getTime();
        System.out.println(("head: "+(end03 - end02)));

        sort.mergeSort(arr);
        long end04 = new Date().getTime();
        System.out.println(("merge: "+(end04 - end03)));
    }
}