package com.eTeng.ds.heap.exception;

/**
 * @FileName UnderFlowException.java
 * @Author 梁怡腾
 * @Date 2018/8/21
 * @Description 数组下溢出异常
 */
public class UnderFlowException extends RuntimeException{

    public UnderFlowException(){
        super();
    }

    public UnderFlowException(String message){
        super(message);
    }
}
