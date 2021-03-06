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
        arr = new Integer[1000];
//        arr = new Integer[]{1,3,54,64,23,7,2,24,652,234,6,44};
        for(int i=0 ; i < 1000; i++){
            arr[i] = random.nextInt(100);
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
    public void bubbleSort(){
        System.out.println(Arrays.toString(arr));
        sort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void selectSort(){
        System.out.println(Arrays.toString(arr));
        sort.selectSort(arr);
        System.out.println(Arrays.toString(arr));
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
    public void qiuckSort(){
//        long start = new Date().getTime();
                System.out.println(Arrays.toString(arr));
        sort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
//        long end = new Date().getTime();
//        System.out.println((end - start));
    }

    @Test
    public void qiuckSelect(){
        Integer [] intArr = new Integer[10000000];
        for(int i=0 ; i < 10000000; i++){
            intArr[i] = random.nextInt(100000);
        }

        long start = new Date().getTime();
        System.out.println(start);
        sort.qiuckSelect(intArr,11);
        long end = new Date().getTime();
        System.out.println(end);
        System.out.println((end - start) / 1000.0);
    }

    @Test
    public void streamlineQiuckSort(){
        sort.streamlineQiuckSort(arr);
        System.out.println(arr);
    }

    @Test
    public void sortPerformance(){
        long start = new Date().getTime();

        sort.insertSort(arr);
        long end01 = new Date().getTime();
        System.out.println("insert: " +(end01 - start));

        sort.selectSort(arr);
        long end02 = new Date().getTime();
        System.out.println("insert: " +(end02- end01));

        sort.bubbleSort(arr);
        long end03 = new Date().getTime();
        System.out.println("insert: " +(end03- end02));

        sort.shellSort(arr);
        long end04 = new Date().getTime();
        System.out.println("shell: "+(end04- end03));

        sort.headSort(arr);
        long end05 = new Date().getTime();
        System.out.println(("head: "+(end05- end04)));

        sort.mergeSort(arr);
        long end06 = new Date().getTime();
        System.out.println(("merge: "+(end06- end05)));

        sort.quickSort(arr);
        long end07 = new Date().getTime();
        System.out.println(("qiuck: "+(end07- end06)));
    }

    @Test
    public void test(){

        //10 * 23
        byte i = 10;
        int j = 0xff;
        int sum = (i<<4) + (i<<3) - (i<<0);
        System.out.println(sum);

    }

    @Test
    public void testBitMap(){

        byte [] bit = new byte[0x7fffff];
        bit[0] = 1;
        bit[1] = 1;
        bit[2] = 1;
        bit[3] = 1;
        bit[10] = 1;

        byte b = bit[100];
        if(b == 1){
            System.out.println("integer is exist");
        }else{
            bit[3] = 1;
        }
    }
}
