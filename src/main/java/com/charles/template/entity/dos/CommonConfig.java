package com.charles.template.entity.dos;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName common_config
 */
@TableName(value = "common_config")
@Data
public class CommonConfig implements Serializable {
    private Integer id;

    @TableField(value = "config_group")
    private String configGroup;

    @TableField(value = "config_key")
    private String configKey;

    private String value;

    private Integer state;

    private String remark;

    private Date lastModifiedTime;

    private static final long serialVersionUID = 1L;
}