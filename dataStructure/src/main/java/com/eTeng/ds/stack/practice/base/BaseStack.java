package com.eTeng.ds.stack.practice.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/7/31
 * @Description
 */
public class BaseStack {

    private static Map<String,BaseStack.Operator> operatorMap;

    public static Map<String,Operator> getOperatorMap(){
        return operatorMap;
    }

    public static void setOperatorMap(Map<String,Operator> operatorMap){
        BaseStack.operatorMap = operatorMap;
    }

    /*
     * 初始化加载操作符的映射
     */
    static {
        operatorMap = new HashMap<String,BaseStack.Operator>();
        BaseStack.Operator[] operators = BaseStack.Operator.values();
        for(BaseStack.Operator operator : operators){
            operatorMap.put(operator.getOperator(),operator);
        }
    }

    public enum Operator{

        ADD(0,"+"),
        SUBTRACT(0,"-"),
        RIDE(1,"*"),
        DIVIDE(1,"/"),
        OPENBRAKET(2,"("),
        CLOSEBRAKET(2,")");

        private int weight; //优先级
        private String operator; //操作符

        Operator(int weight , String operator){

            this.weight = weight;
            this.operator = operator;
        }

        public int getWeight(){
            return weight;
        }

        public void setWeight(int weight){
            this.weight = weight;
        }

        public String getOperator(){
            return operator;
        }

        public void setOperator(String operator){
            this.operator = operator;
        }
    }
}
