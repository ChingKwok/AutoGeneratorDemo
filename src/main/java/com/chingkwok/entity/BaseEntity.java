package com.chingkwok.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by guojingye on 2019/7/17
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 创建用户ID
     */
    private String creatorUserId;

    /**
     * 创建用户名
     */
    private String creatorUserName;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 删除用户ID
     */
    private String deleterUserId;

    /**
     * 删除时间
     */
    private Date deletionTime;

    /**
     * 最新修改人ID
     */
    private String lastModifierUserId;

    /**
     * 最新修改时间
     */
    private Date lastModificationTime;

    /**
     * 软删除（0:存在、1:已删除）
     */
    private Boolean isDeleted;
}
