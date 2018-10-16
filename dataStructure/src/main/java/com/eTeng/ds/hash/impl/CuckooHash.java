package com.eTeng.ds.hash.impl;

import com.eTeng.ds.hash.interfaces.FunctionFamily;
import com.eTeng.ds.hash.interfaces.MyHash;
import java.util.Random;

/**
 * @FileName CuckooHash.java
 * @Author eTeng
 * @Date 2018/8/20
 * @Description
 */
public class CuckooHash<T> implements MyHash<T>{

    /**
     * 最大的填充因子(如果填充因子小于0.5,发生替换或者循环替换的概率极低。)
     */
    private final static double MAX_LOAD = 0.4F;
    /**
     * 插入时允许最大的再散列次数
     */
    private final static int ALLOW_REHASH = 1;
    /**
     * 默认的表大小
     */
    private final static int DEFAULT_TABLE_SIZE = 101;

    /**
     * 散列函数集合实例
     */
    private final FunctionFamily<T> family;
    /**
     *散列函数个数
     */
    private final int functionCount;
    /**
     * 表元素数组
     */
    private T [] elements;
    /**
     * 散列表元素个数
     */
    private int currentSize;


    public CuckooHash(FunctionFamily<T> family){
        this(family,DEFAULT_TABLE_SIZE);
    }

    public CuckooHash(FunctionFamily<T> family,int specifySize){
        allocationArray(specifySize);
        makeEmpty();
        this.family = family;
        this.functionCount = family.functionNum();
    }

    public boolean cantains(T t){
        return findPos(t) != -1;
    }

    public void insert(T t){

        if(cantains(t)){
            return;
        }
        if(currentSize > elements.length * MAX_LOAD){
            expand();
        }
        insertHelper(t);
    }

    public void remove(T t){
        int pos = findPos(t);
        if(pos != -1){
            elements[pos] = null;
        }
        currentSize--;
    }

    public T get(T t){
        int pos = findPos(t);
        if(pos != -1){
            return elements[pos];
        }
        return null;
    }

    public void makeEmpty(){
        for(T element : elements){
          element = null;
        }
        currentSize = 0;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public int size(){
        return currentSize;
    }

    /**
     * 初始化表
     */
    private void allocationArray(int specifySize){
        elements = (T[])new Object[primeNext(specifySize)];
    }

    /**
     * 获取当前数的下一个素数
     * @param size
     * @return
     */
    private int primeNext(int size){
        int expectedSize = size;
        for(int i = 2; i < size; i++){
            if(size % i == 0){
                expectedSize = primeNext(++size);
            }else if(i == size - 1){
                return expectedSize;
            }
        }
        return expectedSize;
    }

    /**
     * 再散列
     */
    private void rehash(){
        family.generatorNewFunction();
        rehash(elements.length);
    }

    /**
     * 扩展表
     */
    private void expand(){
        rehash((int)(elements.length / MAX_LOAD));
    }

    /**
     * 再散列指定长度的散列表
     * @param specifySize
     */
    private void rehash(int specifySize){
        T[] oldElements = elements;
        elements = (T[])new Object[primeNext(specifySize)];
        currentSize = 0;
        //拷贝元素
        for(T element : elements){
            //如果是空不拷贝
            if(element != null){
                insert(element);
            }
        }
    }

    /**
     * 查找适当的索引
     * @param t
     * @return
     */
    private int findPos(T t){

        //遍历散列函数
        for(int i = 0; i < functionCount; i++){
            int hash = getHash(t,i);
            //不为空,并且相等
            while(elements[hash] != null && elements[hash].equals(t)){
                return hash;
            }
        }
        return -1;
    }

    /**
     * 计算hash值
     * @param t
     * @return
     */
    private int getHash(T t , int witch){
        int hash = family.hash(t,witch);
        hash = hash % elements.length;
        if(hash < 0){
            hash += elements.length;
        }
        return hash;
    }

    /**
     * 记录再散列的次数
     */
    private int rehashs = 0;

    /**
     * 生成随机数，用于随机选择散列函数
     */
    private Random r = new Random();

    /**
     * 插入例程
     * @param t
     */
    private void insertHelper(T t){

        final int  LIMITI_COUNT = 100;
        while(true){
            int lastPos = -1;
            int pos;
            //限制替换次数为100
            for(int count = 0; count < LIMITI_COUNT; count++){

                for(int num = 0; num < functionCount; num++){
                    pos = getHash(t,num);
                    //散列位置不需要替换时。
                    if(elements[pos] == null){
                        elements[pos] = t;
                        currentSize++;
                        return;
                    }
                }
                int i = 0;
                do{
                    //随机散列函数计算pos
                    pos = getHash(t,r.nextInt(functionCount));
                }while(lastPos == pos && i++ < 5);
                    //替换后再插入替换的元素
                    T temp = elements[lastPos = pos];
                    elements[pos] = t;
                    t = temp;
            }

            if(++rehashs > ALLOW_REHASH){
                //如果散列次数超过限定,进行扩展
                expand();
                rehashs = 0;
            }else{
                //发生替换之前,进行再散列
                rehash();
            }
        }
    }
}
