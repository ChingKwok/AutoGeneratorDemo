package com.chingkwok.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by guojingye on 2019/7/18
 */
@Data
public class Table {
    private String tableName;
    private List<Column> columns;
    private String tableComment;
}
