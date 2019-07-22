package com.chingkwok;


import com.chingkwok.util.DatabaseUtil;

public class Demo {


    public static void main(String[] args) {
        String project = DatabaseUtil.getAlias("erp_Project_test");
        System.out.println(project);
    }
}