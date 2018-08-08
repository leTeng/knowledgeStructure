package com.eTeng.ds.tree.practice;

import java.io.File;

/**
 * @FileName Practice4_10.java
 * @Author 梁怡腾
 * @Date 2018/8/7
 * @Description 遍历输出目录的所有文件
 */
public class Practice4_10{

    public void printFiles(File file , int depth){
        printFileProp(file,depth);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                printFiles(f,depth + 1);
            }
        }
    }

    public void printFiles(String filePath , int depth){
        printFiles(new File(filePath),depth);
    }
    private void printFileProp(File file,int depth){

        String fileName = file.getName();
        for(int i = 0; i<=depth; i++){
            System.out.print("    ");
        }
        if(file.isDirectory()){
            System.out.printf(fileName.toUpperCase());
        }else{
            System.out.print(fileName);
        }
        System.out.println();
    }
}
