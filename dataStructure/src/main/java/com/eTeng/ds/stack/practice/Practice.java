package com.eTeng.ds.stack.practice;

import com.eTeng.ds.list.interfaces.MyList;
import com.eTeng.ds.stack.exception.Syntaxerror;
import com.eTeng.ds.stack.interfaces.MyStack;


/**
 * @FileName
 * @Author 梁怡腾
 * @Date 2018/7/31
 * @Description
 */
public class Practice{

    /*
     *  练习3.21
     *          一个平衡符号检查程序。对输入的文本信息进行检测，如果是开符号就压入栈中，
     *          如果是闭符号就弹栈和闭符号比较是或相同。不相同报错。如果闭符号时，时空栈，报错。
     *          遍历完文本，栈中还有元素，报错。
     */

    public void checkPascalSymbol(MyList<String> texts , MyStack<String> myStack){

        for(int i = 0; i<texts.size(); i++){
            String text = texts.get(i);
            baseCheck(myStack,text);
            if(text.equals(PascalSymbol.BEGIN.getSymbol())){
                myStack.push(text);
            }else if(text.equals(PascalSymbol.END.getSymbol())){
                popSymbol(myStack,PascalSymbol.BEGIN.getSymbol());
            }
        }
        if(myStack.size() > 0){
            throw new Syntaxerror();
        }
    }

    public void checkJavaSymbol(MyList<String> texts , MyStack<String> myStack){
        for(int i = 0; i<texts.size(); i++){
            String text = texts.get(i);
            baseCheck(myStack,text);
            if(text.equals(JavaSymbol.BEGIN.getSymbol())){
                myStack.push(text);
            }else if(text.equals(JavaSymbol.END.getSymbol())){
                popSymbol(myStack,JavaSymbol.BEGIN.getSymbol());
            }
        }
        if(myStack.size() > 0){
            throw new Syntaxerror();
        }
    }

    //弹出栈元素
    private void popSymbol(MyStack<String> myStack,String symbol){
        String pop = myStack.pop();
        if(pop == null){
            throw new Syntaxerror();
        }
        if(!pop.equals(symbol)){
            throw new Syntaxerror();
        }
    }

    //基础的符号校验
    private void baseCheck(MyStack<String> myStack,String text){

        if(text.equals(PascalSymbol.LEFTBRACKET.getSymbol())){
            myStack.push(text);
        }else if(text.equals(PascalSymbol.RIGTHBRACKET.getSymbol())){
            popSymbol(myStack,PascalSymbol.LEFTBRACKET.getSymbol());
        }else if(text.equals(PascalSymbol.OPENBRACE.getSymbol())){
            myStack.push(text);
        }else if(text.equals(PascalSymbol.CLOSEBRACE.getSymbol())){
            popSymbol(myStack,PascalSymbol.OPENBRACE.getSymbol());
        }else if(text.equals(PascalSymbol.OPENPARENTH)){
            myStack.push(text);
        }else if(text.equals(PascalSymbol.CLOSEPARENTH)){
            popSymbol(myStack,PascalSymbol.OPENPARENTH.getSymbol());
        }
    }
}



enum PascalSymbol {
    OPENPARENTH("("),
    CLOSEPARENTH(")"),
    BEGIN("begin"),
    END("/end"),
    LEFTBRACKET("["),
    RIGTHBRACKET("]"),
    OPENBRACE("{"),
    CLOSEBRACE("}");

    private String symbol;

    PascalSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
}

enum JavaSymbol {
    OPENPARENTH("("),
    CLOSEPARENTH(")"),
    BEGIN("/*"),
    END("*/"),
    LEFTBRACKET("["),
    RIGTHBRACKET("]"),
    OPENBRACE("{"),
    CLOSEBRACE("}");

    private String symbol;

    JavaSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
}