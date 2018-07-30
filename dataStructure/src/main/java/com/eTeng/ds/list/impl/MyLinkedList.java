package com.eTeng.ds.list.impl;

import java.util.*;
import com.eTeng.ds.list.interfaces.MyList;
public class MyLinkedList<T extends Comparable<T>> implements MyList<T>{

    /**
     * 头结点标记
     */
    private Node<T> beginMarker;

    /**
     * 尾节点标记
     */
    private Node<T> endMarker;

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

    /**
     * 初始化一个 myList
     * @param myList
     */
    public MyLinkedList(MyList<T> myList){
        doclear();
        Iterator<T> iterator = myList.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }
    }

    public void add(T t , int index){
        addBefore(t,getNode(index,0,size()));
    }

    public void add(T t){
        add(t,size());
    }

    public void addAll(MyList<T> myList){
        Iterator<T> iterator = myList.iterator();
        while(iterator.hasNext()){
            add(iterator.next());
        }
    }

    public T remove(T t){
        Node<T> node = findElement(t);
        if(node == null){
            throw new NoSuchElementException();
        }
        return remove(node);
    }

    public T remove(Node<T> node){
        return removeBefore(node);
    }

    public T remove(int index){
        return removeBefore(getNode(index));
    }


    public void removeAll(MyList<T> myList){
        Iterator<T> iterator = myList.iterator();
        Iterator thisIterator;
        while(iterator.hasNext()){
            //每一次删除后，重新获取每次迭代器
            thisIterator = iterator();
            T target = iterator.next();
            T next;
            //处理当前集合为空
            while(thisIterator.hasNext()){
                next = (T)thisIterator.next();
                if(target.compareTo(next) == 0){
                    thisIterator.remove();
                    break;
                }else{
                    break;
                }
            }
        }
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

    public T get(int idx){
        return getNode(idx).data;
    }

    public T set(T t , int idx){
        Node<T> node = getNode(idx);
        return set(t,node);
    }

    public T set(T t , Node<T> node){
        T oldDate = node.data;
        node.data = t;
        return oldDate;
    }

    public boolean contain(T t){
        if(findElement(t) != null){
            return true;
        }else{
            return false;
        }
    }

    public int indexof(T t){
        return findElementIndex(t);
    }

    public Iterator iterator(){
        return new LinkedListIterator();
    }


    /**
     * 暂时不提供实现。以后需要实现
     * @return
     */
    public ListIterator listIterator(){
        return new MyListIterator();
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
     * 获取并集
     * @param reference 参考列表
     * @return
     */
    public MyList<T> getUnion(MyList<T> reference){

        MyList<T> union = new MyLinkedList<T>();
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()){
            T next = iterator.next();
            if(isFinedByBinarySearch(next,reference)){
                union.add(next);
            }
        }
        return union;
    }

    /**
     *  获取交集
     */

    public MyList<T> getInterSection(MyList<T> reference){
        Iterator<T> iterator = iterator();
        while(iterator.hasNext()){
            T next = iterator.next();
            if(!isFinedByBinarySearch(next,reference)){
                reference.add(next);
            }
        }
        return reference;
    }

    /**
     * 添加节点主进程
     * @param data
     * @param node
     */
    private void addBefore(T data , Node<T> node){
        Node<T> newNode = new Node<T>(data,node.prev,node);
        node.prev.next = node.prev = newNode;
        this.size++;
        modCount++;
    }

    /**
     * 删除节点主进程
     * @param node 删除节点
     * @return
     */
    private T removeBefore(Node<T> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;
        modCount++;
        return node.data;
    }

    private int findElementIndex(T t){
        Iterator<T> iterator = iterator();
        int indexOf = 0;
        while(iterator.hasNext()){
            if(iterator.next().equals(t)){
                return indexOf;
            }
            indexOf++;
        }
        return -1;
    }

    /**
     * 查找元素节点
     * @param t
     * @return
     */
    private Node<T> findElement(T t){
        Node<T> node = beginMarker;
    	while((node = node.next) != null){
    		if(node == endMarker) {
    			break;
    		}
            if(node.data.equals(t)){
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
    private Node<T> getNode(int idx){
        return getNode(idx,0,size() - 1);
    }

    /**
     * 获取节点
     * @param idx 目标位置
     * @param lower 开始位置
     * @param upper 结束位置
     * @return
     */
    private Node<T> getNode(int idx ,int lower , int upper){

        Node<T> node;
        rangeCheck(idx, lower, upper);
        if(idx < size()  / 2){
            //由左往右遍历
            node = beginMarker;
            for(int i = -1; i < idx; i++){
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
        endMarker = new Node<T>(null,beginMarker,null);
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
        if(idx < 0 || idx > size()){
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *  二分查找
     * @param myList 列表
     * @param target 尋找目標
     * @param left 左边界
     * @param rigth 右边界
     * @return 索引
     */
    private int binarySearch(MyList<T> myList,T target,int left ,int rigth){

        int min = (rigth + left) / 2;
        if(left > rigth){
            return -1;
        }
        T t = myList.get(min);
        if(target.compareTo(t) > 0){
            return binarySearch(myList,target,min + 1,rigth);
        }else if(target.compareTo(t) < 0){
            return binarySearch(myList,target,left,min - 1);
        }else{
            return min;
        }
    }

    private int binarySearch(MyList<T> myList,T target){
        return binarySearch(myList,target,0,myList.size() - 1);
    }

    /**
     * 二分法是否能找到元素
     * @param next
     * @param reference
     * @return
     */
    private boolean isFinedByBinarySearch(T next , MyList<T> reference){
            if(this.binarySearch(reference,next) != -1){
                return true;
        }
        return false;
    }

    /**
     * 二分查找元素
     * @param next
     * @param reference
     * @return
     */
    private T finedByBinarySearch(T next , MyList<T> reference){
        if(this.binarySearch(reference,next) == 0){
           return next;
        }
        return null;
    }

    /**
     *	重写toString
     */
    @Override
    public String toString() {
    	 Iterator<T> iterator = iterator();
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
    
    /**
     * 迭代器
     */
    private class LinkedListIterator implements Iterator<T>{

        /**
         * 当前元素
         */
        private MyLinkedList.Node<T> current = beginMarker.next;

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

        public T next(){
            if(expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            T data = current.data;
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
                throw new IllegalStateException("Before remove elements, Call next() gets the element");
            }
            removeBefore(current.prev);
            allowRemove = false;
            expectedModCount++;
        }

    }

    private class MyListIterator implements ListIterator<T>{

        private MyLinkedList.Node<T> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean allowChange = false;
        private boolean backwards;

        public boolean hasNext(){
            return current != endMarker;
        }

        public T next(){
            effectiveChek();
            T data = current.data;
            current = current.next;
            allowChange = true;
            backwards = true;
            return data;
        }

        public boolean hasPrevious(){
            return current.prev != beginMarker;
        }

        public T previous(){
            effectiveChek();
            current = current.prev;
            T data = current.data;
            allowChange = true;
            backwards = false;
            return data;
        }

        public int nextIndex(){
            throw  new UnsupportedOperationException();
        }

        public int previousIndex(){
            throw  new UnsupportedOperationException();
        }

        public void remove(){
            effectiveChek();
            allowChangeCheck();
            Node<T> node;
            if(backwards){
                //向后迭代
                node = current.prev;
            }else{
               //向前迭代
                node = current;
                current = current.prev;
            }
            MyLinkedList.this.remove(node);
            allowChange = false;
            expectedModCount++;
        }

        public void set(T t){
            effectiveChek();
            MyLinkedList.this.set(t,current);
        }

        public void add(T t){
            effectiveChek();
            MyLinkedList.this.addBefore(t,current.next);
            allowChange = false;
            expectedModCount++;
        }

        private void effectiveChek(){
            if(expectedModCount != modCount){
                throw new ConcurrentModificationException();
            }
        }

        private void allowChangeCheck(){
            if(!allowChange){
                throw new IllegalStateException();
            }
        }

        private void isHasNext(){
            if(!hasNext()){
                throw new IllegalStateException();
            }
        }
    }

    /**
     * 数据抽象（一个节点）
     *      包括：元数据
     *            前驱节点
     *            后置节点
     * @param <T>
     */
    private static class Node<T>{

        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data,Node<T> prev,Node<T> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }
}
