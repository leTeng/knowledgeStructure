package com.eTeng.ds.stack.practice;

import com.eTeng.ds.stack.interfaces.MyStack;
import com.eTeng.ds.stack.practice.base.BaseStack;

import java.util.Map;

/**
 * @FileName
 * @Author eTeng
 * @Date 2018/7/31
 * @Description 计算后缀表达式
 */
public class Practice_3_22 extends BaseStack{

    Map<String,BaseStack.Operator> operatorMap = getOperatorMap();

    public double compute(MyStack<String> myStack , String suffixExpression){
        String[] symbols = suffixExpression.split("");
        return makeCompute(myStack,symbols);
    }

    private double makeCompute(MyStack<String> myStack , String [] symbols){
        for(String symbol : symbols){
            if(operatorMap.get(String.valueOf(symbol)) != null){
                String pop1;
                String pop2;
                if(myStack.top() != null){
                    pop1 = myStack.pop();
                }else{
                    continue;
                }
                if(myStack.top() != null){
                    pop2 = myStack.pop();
                }else{
                    continue;
                }
                double result = 0;
                if(symbol.equals(Operator.SUBTRACT.getOperator())){
                    result = Double.parseDouble(pop2) - Double.parseDouble(pop1);
                }else if(symbol.equals(Operator.ADD.getOperator())){
                    result = Double.parseDouble(pop2) + Double.parseDouble(pop1);
                }else if(symbol.equals(Operator.RIDE.getOperator())){
                    result = Double.parseDouble(pop2) * Double.parseDouble(pop1);
                }else if(symbol.equals(Operator.DIVIDE.getOperator())){
                    result = Double.parseDouble(pop2) / Double.parseDouble(pop1);
                }
                myStack.push(result + "");
            }else{
                myStack.push(String.valueOf(symbol));
            }
        }
        return Double.parseDouble(myStack.pop());
    }
}
