${"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"}
${"<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">"}

<mapper namespace="${project.packageName}.${project.projectCode}.mapper.${table.tableName}Mapper">
    <resultMap id="BaseResultMap" type="${project.packageName}.${project.projectCode}.bean.entity.SendPiece">
        <id column="send_piece_id" jdbcType="BIGINT" property="sendPieceId" />
        <result column="is_agency" jdbcType="TINYINT" property="isAgency" />
        <result column="agent_department_id" jdbcType="VARCHAR" property="agentDepartmentId" />
        <result column="receiver" jdbcType="VARCHAR" property="receiver" />
        <result column="receiver_contact" jdbcType="VARCHAR" property="receiverContact" />
        <result column="receiver_address" jdbcType="VARCHAR" property="receiverAddress" />
        <result column="send_goods" jdbcType="VARCHAR" property="sendGoods" />
        <result column="send_reason" jdbcType="VARCHAR" property="sendReason" />
        <result column="track_number" jdbcType="VARCHAR" property="trackNumber" />
        <result column="send_address" jdbcType="VARCHAR" property="sendAddress" />
        <result column="billing_company" jdbcType="VARCHAR" property="billingCompany" />
        <result column="express_company" jdbcType="VARCHAR" property="expressCompany" />
        <result column="auditor_id" jdbcType="VARCHAR" property="auditorId" />
        <result column="input_files" jdbcType="VARCHAR" property="inputFiles" />
        <result column="is_cancel" jdbcType="TINYINT" property="isCancel" />
        <result column="flow_run_id" jdbcType="INTEGER" property="flowRunId" />
        <result column="flow_state" jdbcType="TINYINT" property="flowState" />
        <result column="creator_user_name" jdbcType="VARCHAR" property="creatorUserName" />
        <result column="creator_user_id" jdbcType="VARCHAR" property="creatorUserId" />
        <result column="creation_time" jdbcType="TIMESTAMP" property="creationTime" />
        <result column="last_modifier_user_id" jdbcType="VARCHAR" property="lastModifierUserId" />
        <result column="last_modification_time" jdbcType="TIMESTAMP" property="lastModificationTime" />
        <result column="deleter_user_id" jdbcType="VARCHAR" property="deleterUserId" />
        <result column="deletion_time" jdbcType="TIMESTAMP" property="deletionTime" />
        <result column="is_deleted" jdbcType="TINYINT" property="isDeleted" />
    </resultMap>
