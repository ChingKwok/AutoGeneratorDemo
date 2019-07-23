package com.chingkwok;


import com.chingkwok.util.DatabaseUtil;
import com.chingkwok.util.SqlToJavaType;

public class Demo {


    public static void main(String[] args) {
        String s = SqlToJavaType.sqlTojavaHandle(-1);
        System.out.println(s);
    }
}