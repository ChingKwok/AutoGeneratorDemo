package com.chingkwok.util;

import com.chingkwok.util.eum.JavaType;

/**
 * Created by guojingye on 2019/7/23
 */
public class SqlToJavaType {

    public static String sqlTojavaHandle(int sqlTypeCode){
        switch (sqlTypeCode){
            case -7:
            case -6:
            case -4:
            case -3:
            case -2:return JavaType.BOOLEAN.getName();
            case -5:return JavaType.LONG.getName();
            case 0:return "";
            case 1:
            case 2:
            case 3:return JavaType.DECIMAL.getName();
            case 4:return JavaType.INTEGER.getName();
            case 5:return JavaType.SHORT.getName();
            case 6:return JavaType.FLOAT.getName();
            case 7:return "";
            case 8:return JavaType.DOUBLE.getName();
            case -1:
            case 12:return JavaType.STRING.getName();
            case 91:
            case 92:
            case 93:return JavaType.DATE.getName();

        }
        return "";
    }
}

//        -7	BIT
//        -6	TINYINT
//        -5	BIGINT
//        -4	LONGVARBINARY
//        -3	VARBINARY
//        -2	BINARY
//        -1	LONGVARCHAR
//        0	NULL
//        1	CHAR
//        2	NUMERIC
//        3	DECIMAL
//        4	INTEGER
//        5	SMALLINT
//        6	FLOAT
//        7	REAL
//        8	DOUBLE
//        12	VARCHAR
//        91	DATE
//        92	TIME
//        93	TIMESTAMP
//        1111 	OTHER