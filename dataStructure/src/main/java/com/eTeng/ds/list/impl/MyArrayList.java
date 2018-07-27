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

    public void add(AnyType anyType){
         add(anyType,size());
    }

    public void addAll(MyList<AnyType> myList){
        Iterator<AnyType> iterator = myList.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }
    }

    public void removeAll(MyList<AnyType> myList){
        for(int idx = 0; idx < myList.size(); idx++){
            AnyType anyType = myList.get(idx);
            for(int cpaIdx = idx; cpaIdx < size(); cpaIdx++){
                if(anyType.equals(get(cpaIdx))){
                    remove(cpaIdx);
                    break;
                }
            }
        }
    }

    public AnyType remove(AnyType anyType){
        Iterator<AnyType> iterator = iterator();
        for(int idx=0; iterator.hasNext() ; idx++){
            if(iterator.next().equals(anyType)){
                remove(idx);
            }
        }
        size--;
        return anyType;
    }

    public AnyType remove(){
        return remove(size()-1);
    }
    public AnyType remove(int idx){
    	  checkRange(idx);
          AnyType removeEle = elements[idx];
          elements[idx] = null;
          for(int i = idx; i < size() - 1; i++){
              elements[i] = elements[i+1];
          }
          size--;
          return removeEle;
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
        return new MyIterator();
    }

    public ListIterator<AnyType> listIterator(){
        return null;
    }


    private void checkRange(int idx){
        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String toString(){
        Iterator<AnyType> iterator = iterator();
        StringBuilder sb = new StringBuilder("[");
        for(;iterator.hasNext();){
            sb.append(iterator.next());
            if(iterator.hasNext()) {
            	sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public class MyIterator<AnyType> implements Iterator<AnyType>{

    	//当前光标
    	private int current = 0;
    	
		public boolean hasNext() {
			return  MyArrayList.this.size() > current;
		}

		public AnyType next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return (AnyType) elements[current++];
		}

        public void remove(){
            MyArrayList.this.remove(--current);
        }

    }  
}
