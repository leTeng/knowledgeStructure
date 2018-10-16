package com.eTeng.ds.hash.impl;
import com.eTeng.ds.hash.interfaces.MyHash;
import com.eTeng.ds.list.impl.MyLinkedList;
import java.util.Iterator;

/**
 * @FileName SeparateChainHashTable.java
 * @Author eTeng
 * @Date 2018/8/16
 * @Description
 */
public class SeparateChainHashTable<T extends Comparable<T>> implements MyHash<T>{

    /**
     * 默认表的大小
     */
    private static final int DEFAULT_TABLE_SIZE = 3;

    /**
     * 分离链表子表元素
     */
    private MyLinkedList<T>[] elements;

    /**
     * 表的元素数量
     */
    private int size;

    public SeparateChainHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainHashTable(int tableSize){
        elements = new MyLinkedList[nextPrime(tableSize)];
        allocateList(elements.length);
        size = 0;
    }

    /**
     * 是否包含,依赖元素的hashcode()和equals()
     * @param t
     * @return
     */
    public boolean cantains(T t){
        MyLinkedList<T> elementList = elements[getHash(t)];
        return elementList.contain(t);
    }

    public void insert(T t){

        MyLinkedList<T> elementList = elements[getHash(t)];
        if(!elementList.contain(t)){
            elementList.addFirst(t);
            //当λ=1或λ≈1时,扩容
            if(++size > elements.length){
                //散列表表扩容
                rehash();
            }
        }
    }

    public void remove(T t){
        MyLinkedList<T> elementList = elements[getHash(t)];
        if(!elementList.contain(t)){
            return;
        }
        elementList.remove(t);
        size--;
    }

    public T get(T t){
        MyLinkedList<T> elementList = elements[getHash(t)];
        Iterator iterator = elementList.iterator();
        while(iterator.hasNext()){
            T next = (T)iterator.next();
            if(next.equals(t)){
                return next;
            }
        }
        return null;
    }

    public void makeEmpty(){
        for(int i = 0; i < elements.length; i++){
            elements[i] = null;
        }
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    /**
     * 获取映射位置
     * @param t
     * @return
     */
    private int getHash(T t){
        int hashVal = t.hashCode();
        hashVal %= elements.length;
        if(hashVal < 0){
            hashVal += elements.length;
        }
        return hashVal;
    }

    /**
     * 散列表扩容
     */
    private void rehash(){
        MyLinkedList [] oldElements = elements;
        elements = new MyLinkedList [nextPrime(oldElements.length * 2)];
        allocateList(elements.length);
        for(MyLinkedList oldElementList : oldElements){
            for(Object o : oldElementList){
                insert((T)o);
            }
        }
    }

    /**
     * 计算表大小后一个素数
     * @param size
     * @return
     */
    private int nextPrime(int size){
        int primeSize = size;
        for(int i = 2; i < size ; i++){
            if(size % i == 0){
                primeSize = nextPrime(++size);
            }else if(i == size -1){
                return size;
            }
        }
        return primeSize;
    }

    /**
     * 分配list
     * @param size
     */
    private void allocateList(int size){
        for(int i = 0; i < size; i++){
            elements[i] = new MyLinkedList<T>();
        }
    }
}
