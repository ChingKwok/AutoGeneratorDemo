package com.chingkwok.entity;

import lombok.Data;

/**
 * Created by guojingye on 2019/7/19
 */
@Data
public class Column {
    private String name;
    private String typeName;
    private Integer typeCode;
    private String comment;
    private String property;
    private Boolean isPrimary;
}
