package com.eTeng.ds.tree.binarytree;

import com.eTeng.ds.stack.impl.MyLinkedStack;
import com.eTeng.ds.stack.interfaces.MyStack;
import com.eTeng.ds.stack.practice.Practice_3_23_a;
import com.eTeng.ds.stack.practice.base.BaseStack;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/8/1
 * @Description 二叉树的一种应用。表达式树
 */
public class ExpressionTree{


    public void buildExpreTree(String infixExpre){

        String suffixExpre = convert(infixExpre);
        String[] strs = suffixExpre.split("");
        MyStack<Node<String>> myStack = new MyLinkedStack<Node<String>>();
        Node<String> node = null;
        for(String str : strs){
            if(BaseStack.getOperatorMap().get(str) != null){
                Node<String> operand1;
                Node<String> operand2;
                if(myStack.top() != null){
                    operand1 = myStack.pop();
                }else{
                    continue;
                }
                if(myStack.top() != null){
                    operand2 = myStack.pop();
                }else{
                    continue;
                }
                //操作符弹栈
                node = new Node<String>(str,operand1,operand2);
                //构建节点再压栈
                myStack.push(node);
            }else{
                //操作数压栈
                myStack.push(new Node<String>(str,null,null));

            }
        }
    }


    private String convert(String infixExpre){
        Practice_3_23_a practice = new Practice_3_23_a();
        return practice.convertSuffix(infixExpre,new MyLinkedStack<String>());
    }

    private class Node<T> {

        public Node(T data,Node<T> left,Node<T> rigth){
            this.data = data;
            this.left = left;
            this.rigth = rigth;
        }

        public T data; //数据
        public Node<T> left; //左子树
        public Node<T> rigth;//右子树
    }
}
