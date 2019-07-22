${"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"}
${"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"}

<mapper namespace="${project.packageName}.${project.projectCode}.mapper.${table.tableName}Mapper">
    <resultMap id="BaseResultMap" type="${project.packageName}.${project.projectCode}.bean.entity.SendPiece">
        <#list table.columns as column>
            <#if (column.isPrimary)>
                <id column="${column.name}" jdbcType="${column.typeName}" property="${column.property}"/>
            <#else >
                <result column="${column.name}" jdbcType="${column.typeName}" property="${column.property}"/>
            </#if>
        </#list>
    </resultMap>

    <sql id="Base_Column_List">
        <#list table.columns as column>
            ${table.alias}.${column.name}<#if column?has_next>,</#if>
        </#list>
    </sql>

    <sql id="BASE_SELECT_BY_MAP">
        select
        <include refid="Base_Column_List"/>
        from ${table.tableName} ${table.alias} where ${table.alias}.is_deleted = 0
        <if test="_parameter != null">
            <include refid="ByMap_Where_Clause"/>
        </if>
        <if test="orderByClause != null">
            order by ${"$"}{orderByClause}
        </if>
        <if test="sortName != null">
            <include refid="ByMap_OrderBy_Clause"/>
        </if>
    </sql>

    <select id="selectByMap" parameterType="java.util.Map" resultMap="BaseResultMap">
        <include refid="BASE_SELECT_BY_MAP"/>
    </select>
    <select id="selectOneByMap" parameterType="java.util.Map" resultMap="BaseResultMap">
        <include refid="BASE_SELECT_BY_MAP"/>
        limit 1
    </select>



</mapper>