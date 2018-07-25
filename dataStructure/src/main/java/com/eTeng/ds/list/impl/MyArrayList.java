package com.eTeng.ds.list.impl;

import com.eTeng.ds.list.interfaces.MyList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements MyList<AnyType>{
    /**
     * 初始化容量
     */
    private int DEFAULT_CAPACITY = 10;

    /**
     * 最大容量
     */
    private int MAX_CAPACITY = 0x7fffff;

    /**
     * 数据容器
     */
    private AnyType [] elements;

    /**
     * 当前元素数量
     */
    private int size;

    public MyArrayList(int initCapacity){
        ensureCapacity(initCapacity);
    }

    public MyArrayList(){
        elements = (AnyType [])new Object[DEFAULT_CAPACITY];
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    public AnyType get(int idx){
        checkRange(idx);
        return elements[idx];
    }


    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }


    public void clear(){
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public void add(AnyType anyType,int index){
        checkRange(index);
        if(elements.length < size() + 1){
            ensureCapacity(size << 1);
        }
        for(int i = size(); i > index ; i--){
            elements[i] = elements[i - 1];
        }
        elements[index] = anyType;
        size++;
    }

    public boolean add(AnyType anyType){
         add(anyType,size());
         return true;
    }

    public boolean remove(AnyType anyType){
        return false;
    }

    public void remove(int idx){
    	  checkRange(idx);
          AnyType removeEle = elements[idx];
          for(int i = idx; i < size() - 1; i++){
              elements[i] = elements[i+1];
          }
          size--;
//          return removeEle;
    }
    public AnyType set(AnyType anyType,int idx){
    	AnyType old = elements[idx];
    	elements[idx] = anyType;
    	return old;
    }

    public boolean contain(AnyType anyType){
        for(int i = 0; i < size(); i++){
            AnyType element = elements[i];
            if(element.equals(anyType)){
                return true;
            }
        }
        return false;
    }

    /**
       * 扩容
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity){
        if(newCapacity < size()){
            return;
        }
        AnyType [] newElements = (AnyType [])new Object[newCapacity];
        for(int i = 0; i < elements.length; i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    public Iterator<AnyType> iterator(){
        return null;
    }

    public ListIterator<AnyType> listIterator(){
        return null;
    }


    private void checkRange(int idx){
        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }
    }
    
    public class MyIterator<AnyType> implements Iterator<AnyType>{

    	//当前光标
    	private int current = 0;
    	
		public boolean hasNext() {
			return  size() > current;
		}

		public AnyType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return (AnyType) elements[current++];
		}

        public void remove(){

        }

    }  
}
