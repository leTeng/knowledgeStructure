package com.eTeng.ds.stack.practice;
import com.eTeng.ds.stack.interfaces.MyStack;
import com.eTeng.ds.stack.practice.base.BaseStack;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/7/31
 * @Description
 */
public class Practice_3_23_a extends BaseStack{

    Map<String,BaseStack.Operator> operatorMap = getOperatorMap();
    //将一个中序表达式转换为后缀表达式
    public String convertSuffix(String middleOrder,MyStack<String> myStack){

        List<String> strs = Arrays.asList(middleOrder.split(" "));
        StringBuilder conversion =  new StringBuilder();
            for(String str : strs){
                if(operatorMap.get(str) == null){
                    conversion.append(str);
                    continue;
                }
                makePop(myStack,str,conversion);

            }
        while(myStack.top() != null){
            conversion.append(myStack.pop());
        }
        return conversion.toString();
    }

    /**
     * 弹栈操作
     * @param myStack 操作符栈
     * @param str 当前操作符
     * @param conversion 输出操作符
     */
    private void makePop(MyStack<String> myStack , String str , StringBuilder conversion){

        boolean hasBraket = str.equals(BaseStack.Operator.CLOSEBRAKET.getOperator());
        String pop;
        if(myStack.top() == null){
            if(!hasBraket){
                myStack.push(str);
            }
            return;
        }
        pop = pop(myStack);
        BaseStack.Operator operator = operatorMap.get(pop);
        BaseStack.Operator nextOperator = operatorMap.get(str);
        if(!hasBraket){
            //处理 --
            if(operator.equals(nextOperator) &&
                    nextOperator.getOperator().equals(BaseStack.Operator.SUBTRACT)){
                conversion.append(pop);
                return;
            }
            //正常情况
            while(operator != null && !operator.equals(BaseStack.Operator.OPENBRAKET)
                    && operator.getWeight() > nextOperator.getWeight()){
                //弹栈
                if(pop != null){
                    conversion.append(pop);
                    pop = pop(myStack);
                    operator = operatorMap.get(pop);
                }else{
                    break;
                }
            }
        }else{
            while(!operator.equals(BaseStack.Operator.OPENBRAKET)){
                if(myStack.top() == null){
                    break;
                }
                if(!pop.equals(BaseStack.Operator.CLOSEBRAKET.getOperator()) &&
                        !pop.equals(BaseStack.Operator.OPENBRAKET.getOperator())){
                    conversion.append(pop);
                }
                pop = pop(myStack);
                operator = operatorMap.get(pop);
            }
        }
        if(pop != null && !hasBraket){
            myStack.push(pop);
        }
        if(!hasBraket){
            myStack.push(str);
        }
    }

    private String pop(MyStack<String> myStack){
        if(myStack.top() != null){
            return myStack.pop();
        }
        return null;
    }

}
