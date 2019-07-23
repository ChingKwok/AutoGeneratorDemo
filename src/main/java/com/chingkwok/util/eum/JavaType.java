package com.chingkwok.util.eum;

import lombok.Getter;

/**
 * Created by guojingye on 2019/7/23
 */
@Getter
public enum JavaType {
    BOOLEAN("Boolean"),
    STRING("String"),
    DECIMAL("Decimal"),
    INTEGER("Integer"),
    LONG("Long"),
    DOUBLE("Double"),
    SHORT("Short"),
    FLOAT("Float"),
    DATE("Date")
    ;
    private  String name;
    JavaType(String name){
        this.name = name;
    }
}
