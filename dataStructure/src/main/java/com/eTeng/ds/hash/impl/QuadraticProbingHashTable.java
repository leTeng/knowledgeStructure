package com.eTeng.ds.hash.impl;

import com.eTeng.ds.hash.interfaces.MyHash;

/**
 * @FileName QuadraticProbingHashTable.java
 * @Author eTeng
 * @Date 2018/8/17
 * @Description
 */
public class QuadraticProbingHashTable<T> implements MyHash<T>{

    /**
     * 散列表
     */
    private HashEntry<T> [] elements;

    /**
     * 表元素个数
     */
    private int size;

    /**
     * 默认表大小
     */
    private static final int DEFAULT_TABLE_SIZE = 101;

    public QuadraticProbingHashTable(){
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int specifySize){
        capacity(specifySize);
        makeEmpty();
    }

    public boolean cantains(T t){
        int pos = findPos(t);
        return isActive(pos);
    }

    public void insert(T t){
        int pos = findPos(t);
        if(isActive(pos)){
            return;
        }
        elements[pos] = new HashEntry<T>(t,true);
        if(++size > elements.length / 2){
            capacity(elements.length * 2);
        }
    }

    public void remove(T t){
        int pos = findPos(t);
        if(isActive(pos)){
            elements[pos].isActive = false;
            size--;
        }
    }

    public T get(T t){
        int pos = findPos(t);
        if(isActive(pos)){
            return elements[pos].element;
        }
        return null;
    }

    public void makeEmpty(){
        size = 0;
        for(HashEntry<T> element : elements){
            element = null;
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }


    /**
     * 计算散列位置(散列函数)
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
     * @param size
     */
    private void capacity(int size){
        HashEntry[] newElements = new HashEntry[nextPrime(size)];
        if(elements == null){
            elements = newElements;
        }else{
            HashEntry [] oldElements = elements;
            elements = newElements;
            this.size = 0;
            for(HashEntry oldElement : oldElements){
                if(oldElement != null && oldElement.isActive){
                    insert((T)oldElement.element);
                }
            }
        }
    }

    /**
     * 计算最佳的表大小,当前表达大小的下一个素数
     * @param size
     * @return
     */
    private int nextPrime(int size){
        int expectedSize = size;
        for(int i = 2; i < size; i++){
            if(size % 2 == 0){
                expectedSize = nextPrime(++size);
            }else if(i == size - 1){
                return size;
            }
        }
        return expectedSize;
    }

    /**
     * 平方探测寻址。第一次2º 第二次 2² 第三次 2³
     * @param t
     * @return
     */
    private int findPos(T t){
        int offSet = 1;
        int currentPos = getHash(t);
        while(elements[currentPos] != null &&
                !elements[currentPos].element.equals(t)){
            currentPos += offSet;
            offSet += 2;
            if(currentPos >= elements.length){
                currentPos -= elements.length;
            }
        }
        return currentPos;
    }

    private static class HashEntry<T>{

        private boolean isActive; //是否活跃(false删除,true正常)
        private T element;

        public HashEntry(T element,boolean isActive){
            this.isActive = isActive;
            this.element = element;
        }

        public boolean isActive(){
            return isActive;
        }

        public void setActive(boolean active){
            isActive = active;
        }

        public T getElement(){
            return element;
        }

        public void setElement(T element){
            this.element = element;
        }
    }


    private boolean isActive(int pos){
        return elements[pos] != null && elements[pos].isActive;
    }
}
