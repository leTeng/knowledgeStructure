package com.eTeng.ds.list.practice;

import com.eTeng.ds.list.interfaces.MyList;

import java.util.ListIterator;

/**
 * @FileName
 * @Author eTeng
 * @Date 2018/7/27
 * @Description 表、栈、队的练习题
 */
public class Practice<T>{

    //练习3.4：给定两个已经排序的L1、L2,求L1 ∩ L2

         // 参考MyLienkedList类中的getUnion();

    //练习3.5：给定两个已经排序的L1、L2,求L1 ∪ L2

        // 参考MyLienkedList类中的getIntersection();

    //练习3.5 求josephus问题

    /**
     *
     * @param itr
     * @param lst
     */
    public void splice(ListIterator<T> itr,MyList<T> lst){
        while(itr.hasNext()){
            T next = itr.next();
            itr.remove();
            lst.add(next,0);
        }
    }
}
