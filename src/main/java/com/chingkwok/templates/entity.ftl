package ${project.packageName}.${project.projectCode}.bean.entity;

import lombok.Data;
import com.chingkwok.entity.BaseEntity;

<#if (table.tableComment)?exists>
/**
*   ${(table.tableComment)!""}
*/
</#if>

@Data
public class ${table.entityName} extends BaseEntity {
    private static final long serialVersionUID = 1L;

<#list table.columns as column>
    <#if (column.comment)?exists>
    /**
    *   ${(column.comment)!""}
    */
    </#if>
    private ${column.javaType} ${column.property};

</#list>

}