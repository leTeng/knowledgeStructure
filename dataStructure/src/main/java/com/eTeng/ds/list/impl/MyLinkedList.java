package com.eTeng.ds.list.impl;

import com.eTeng.ds.list.interfaces.MyList;
import sun.plugin.dom.exception.InvalidStateException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements MyList<AnyType>{

    /**
     * 头结点标记
     */
    private Node<AnyType> beginMarker;

    /**
     * 尾节点标记
     */
    private Node<AnyType> endMarker;

    /**
     * ADT 元素个数
     */
    private int size;

    /**
     * ADT 结构改变次数 (包括 add() remove() clear() 等)
     */
    private int modCount;


    public MyLinkedList(){
        doclear();
    }


    public void add(AnyType anyType , int index){
        addBefore(anyType,getNode(index));
    }

    public void add(AnyType anyType){
        add(anyType,size());
    }

    public AnyType remove(AnyType anyType){
        Node<AnyType> node = findElement(anyType);
        if(node == null){
            throw new NoSuchElementException();
        }
        return removeBefore(node);
    }

    public AnyType remove(int index){
        return removeBefore(getNode(index));
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void clear(){
        doclear();
    }

    public AnyType get(int idx){
        return getNode(idx).data;
    }

    public AnyType set(AnyType anyType , int idx){
        Node<AnyType> node = getNode(idx);
        AnyType oldDate = node.data;
        node.data = anyType;
        return oldDate;
    }

    public boolean contain(AnyType anyType){
        if(findElement(anyType) != null){
            return true;
        }else{
            return false;
        }
    }

    public int indexof(AnyType anyType){
        return findElementIndex(anyType);
    }

    public Iterator iterator(){
        return new LinkedListIterator();
    }


    /**
     * 暂时不提供实现。以后需要实现
     * @return
     */
    public ListIterator listIterator(){
        throw new UnsupportedOperationException();
    }

    /**
     * 由于接口定义问题。MyLinkedList性质不提供该操作
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity){
        throw new UnsupportedOperationException();
    }

    /**
     * 由于接口定义问题。MyLinkedList性质不提供该操作
     */
    public void trimToSize(){
        throw new UnsupportedOperationException();
    }

    /**
     * 添加节点主进程
     * @param data
     * @param node
     */
    private void addBefore(AnyType data , Node<AnyType> node){
        Node<AnyType> newNode = new Node<AnyType>(data,node.prev,node);
        node.prev.next = node.prev = newNode;
        this.size++;
        modCount++;
    }

    /**
     * 删除节点主进程
     * @param node 删除节点
     * @return
     */
    private AnyType removeBefore(Node<AnyType> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;
        modCount++;
        return node.data;
    }

    private int findElementIndex(AnyType anyType){
        Iterator<AnyType> iterator = iterator();
        int indexOf = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(anyType)){
                return indexOf;
            }
            indexOf++;
        }
        return -1;
    }

    /**
     * 查找元素节点
     * @param anyType
     * @return
     */
    private Node findElement(AnyType anyType){
        for(Node node = beginMarker; node != endMarker; node = node.next){
            if(node.data.equals(anyType)){
                return node;
            }
        }
        return null;
    }
    /**
     * 默认开始位置 lower:0 upper:size
     * @param idx 目标位置
     * @return
     */
    private Node<AnyType> getNode(int idx){
        return getNode(idx,0,size() - 1);
    }

    /**
     * 获取节点
     * @param idx 目标位置
     * @param lower 开始位置
     * @param upper 结束位置
     * @return
     */
    private Node<AnyType> getNode(int idx ,int lower , int upper){

        Node node;
        rangeCheck(idx, lower, upper);
        if(idx < (upper - lower)  / 2){
            //由左往右遍历
            node = beginMarker;
            for(int i = idx; i < idx; i++){
                node = node.next;
            }
        }else{
            //由右往左遍历
            node = endMarker;
            for(int i = size(); i > idx; i--){
                node = node.prev;
            }
        }
        return node;
    }

    /**
     * 清除操作
     */
    private void doclear(){
        beginMarker = new Node(null,null,null);
        endMarker = new Node<AnyType>(null,beginMarker,null);
        beginMarker.next = endMarker;
        size = 0;
        modCount++;
    }

    /**
     * 范围检查
     * @param idx 目标位置
     * @param lower 开始位置
     * @param upper 结束位置
     */
    private void rangeCheck(int idx , int lower , int upper){
        if(idx < lower || idx > upper){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 迭代器
     */
    private class LinkedListIterator implements Iterator<AnyType>{

        /**
         * 当前元素
         */
        private Node<AnyType> current = beginMarker;

        /**
         * 限制了通过 next() 之后才允许删除。默认不能删除
         */
         private boolean allowRemove = false;

        /**
         * 构建时初始化为 ADT modCount快照
         */
        private int expectedModCount = modCount;

        public boolean hasNext(){
            return current != endMarker;
        }

        public AnyType next(){
            if(expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            AnyType data = current.data;
            //推进下一个节点
            current = current.next;
            //标记为允许删除
            allowRemove = true;
            return data;
        }

        public void remove(){
            if(expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
            if(!allowRemove){
                throw new InvalidStateException("Before remove elements, Call next() gets the element");
            }
            removeBefore(current.prev);
            allowRemove = false;
            expectedModCount++;
        }

    }

    /**
     * 数据抽象（一个节点）
     *      包括：元数据
     *            前驱节点
     *            后置节点
     * @param <AnyType>
     */
    private static class Node<AnyType>{

        AnyType data;
        Node<AnyType> prev;
        Node<AnyType> next;

        public Node(AnyType data,Node<AnyType> prev,Node<AnyType> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
