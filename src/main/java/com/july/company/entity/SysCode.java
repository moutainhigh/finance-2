package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 系统字典表
 * @author zengxueqi
 * @since 2020-05-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_sys_code")
public class SysCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 字典类型
     */
    @TableField("codeType")
    private String codeType;
    /**
     * 字典名称
     */
    @TableField("codeName")
    private String codeName;
    /**
     * 字典编码
     */
    @TableField("code")
    private String code;
    /**
     * 字典值
     */
    @TableField("value")
    private String value;

}
