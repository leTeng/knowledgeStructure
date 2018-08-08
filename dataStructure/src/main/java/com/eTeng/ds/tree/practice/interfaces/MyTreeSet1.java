package com.eTeng.ds.tree.practice.interfaces;

import com.eTeng.ds.tree.binarytree.interfaces.MyComparator;
import com.eTeng.ds.tree.practice.impl.MyTreeSet;
import java.util.Collection;
import java.util.Iterator;

/**
 * @FileName MyTreeSet1.java
 * @Author 梁怡腾
 * @Date 2018/8/8
 * @Description
 */
public class MyTreeSet1<T> implements MyTreeSet<T>{

    private TreeSetNode<T> root;

    private int modCount = 0;

    private int size = 0;

    private MyComparator<T> myComparator;

    public MyTreeSet1(){
        this(null);
    }

    public MyTreeSet1(MyComparator<T> myComparator){
        this.myComparator = myComparator;
        this.modCount = 0;
        this.size = 0;
    }

    public void remove(T t){

    }

    public void removeAll(Collection<T> collection){

    }

    public void add(T t){

    }

    public int size(){
        return 0;
    }

    public void addAll(Collection<T> collection){

    }

    public boolean contians(T t){
        return false;
    }

    public void set(T t){

    }

    public boolean isEmpty(){
        return false;
    }

    public void makeEmpty(){

    }

    public T findMin(){
        return null;
    }

    public T findMax(){
        return null;
    }

    public T get(int index){
        return null;
    }

    public Iterator<T> iterator(){
        return null;
    }

    private static class TreeSetNode<T>{

         T data;
         TreeSetNode<T> left;
         TreeSetNode<T> right;
         TreeSetNode<T> parent;

        public TreeSetNode(T data,TreeSetNode<T> parent){
            this(data,null,null,parent);
        }

        public TreeSetNode(T data){
            this(data,null,null,null);
        }

        public TreeSetNode(T data,TreeSetNode<T> left,
                           TreeSetNode<T> right,TreeSetNode<T> parent){
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private class MyIterator<T> implements Iterator<T>{

        public void remove(){

        }

        public boolean hasNext(){
            return false;
        }

        public T next(){
            return null;
        }
    }

    private enum Find
}
