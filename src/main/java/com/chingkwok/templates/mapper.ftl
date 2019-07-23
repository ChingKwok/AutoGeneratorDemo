${"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"}
${"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"}

<mapper namespace="${project.packageName}.${project.projectCode}.mapper.${table.tableName}Mapper">
    <resultMap id="BaseResultMap" type="${project.packageName}.${project.projectCode}.bean.entity.${table.entityName}">
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

    <sql id="ByMap_Where_Clause">
        <#list table.columns as column>
        <#--            类型为：-5,2,3,4,5,6,8-->
            <#if column.typeCode == -5 || column.typeCode == 4 || column.typeCode == 5 || column.typeCode == 6 || column.typeCode == 3 || column.typeCode == 2 || column.typeCode == 8>
                <if test="eq${column.property?cap_first} != null">
                    and ${table.alias}.${column.name} = ${"#"}{eq${column.property?cap_first},jdbcType=${column.typeName}}}
                </if>
            <#--            类型为字符串类型-->
            <#elseif column.typeCode == -1 || column.typeCode == 1 || column.typeCode == 12>
                <if test="like${column.property?cap_first} != null">
                    and ${table.alias}.${column.name} like CONCAT('%',${"#"}{like${column.property?cap_first},jdbcType=${column.typeName}},'%')
                </if>
            <#elseif  (column.typeCode >= 91) && (column.typeCode <= 93)>
                <if test="gt${column.property?cap_first} != null">
                    and ${table.alias}.${column.name} <![CDATA[ > ]]> ${"#"}{gt${column.property?cap_first},jdbcType=${column.typeName}}
                </if>
                <if test="lt${column.property?cap_first} != null">
                    and ${table.alias}.${column.name} <![CDATA[ < ]]>  ${"#"}{lt${column.property?cap_first},jdbcType=${column.typeName}}
                </if>
            </#if>
        </#list>
    </sql>

    <sql id="ByMap_OrderBy_Clause">
        <trim prefix="order by ">
            <#list table.columns as column>
            <if test="sortName == '${column.property}'">
                ${table.alias}.${column.name}
            </if>
            </#list>
            <if test="sortOrder == 'desc'">
                desc
            </if>
            <if test="sortOrder == 'asc'">
                asc
            </if>
        </trim>
    </sql>
</mapper>