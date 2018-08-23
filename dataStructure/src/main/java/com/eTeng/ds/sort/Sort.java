package com.eTeng.ds.sort;

/**
 * @FileName Sort.java
 * @Author 梁怡腾
 * @Date 2018/8/23
 * @Description
 */
public class Sort<T extends Comparable<? super T>>{

    /**
     * 插入排序,平均时间复杂度为O(n^2),如果是已经排序的平均复杂度为O(n)。
     *  如序列： 1 3 4 5 下一个比较为 2 ,循环向已排序的序列标比较插入,首先比较5,
     *          满足5向后移动一位,2和4比较,4向后移动一位,直到和1比较,不满足。在1
     *          后面插入,结果为 1 2 3 4 5。
     *
     *  运行时间分析：
     * @param arr
     */
    public void insertSort(T [] arr){

        for(int i = 0; i < arr.length; i++){
            T temp = arr[i];
            if(temp == null){
                return;
            }
            int j;
            //循环与之前已排序的序列比较,如果满足,插入位置后的元素向后移动一位,然后插入。
            for(j=i; j>0 && temp.compareTo(arr[j-1] ) < 0; j--){
                arr[j] = arr[j-1];
            }
            arr[j] = temp;
        }
    }


    /**
     * 希尔排序(缩减增量排序)：使用一个增量序列,这个序列的增量是递减的。对于n个元素的数组元素k与
     *                      k+Δ比较满足并且插入(交换数据)。对于每次Δ的递减,都进行一次k与k+Δ
     *                      的比较和插入,直到增量为0排序完成。最坏时间复杂度为O(n^2)。
     *
     *      希尔增量算法：Δ₁ = array.length / 2 、 Δk = Δk+1 / 2
     *      hibbard增量算法：Δ₁ = 1 Δ₂ = 3 Δ₃ = 5 .... Δ = 2ⁿ - 1,
     *                      该算法的时间复杂度为O(n^3/2)
     * @param arr
     */
    public void shellSort(T [] arr){

        //递减增量 Δ₁ = array.length / 2 、 Δk = Δk+1 / 2
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i=gap; i<arr.length; i++){
                T temp = arr[i];
                if(temp == null){
                    break;
                }
                int j;
                for(j = i; j > gap && temp.compareTo(arr[j-gap]) < 0; j -= gap){
                    arr[j] = arr[j-gap];
                }
                arr[j] = temp;
            }
        }
    }
}
