package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.base.BaseEntity;
import lombok.*;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    /**
     * 是否是查询条件(0=否，1=是)
     */
    @TableField("boolQuery")
    private Integer boolQuery;
    /**
     * 是否展示(0=隐藏，1=展示)
     */
    @TableField("boolShow")
    private Integer boolShow;
    /**
     * 查询条件顺序
     */
    @TableField("querySort")
    private Integer querySort;
    /**
     * 融资类型(0=股权融资,1=债券融资)
     */
    @TableField("financeType")
    private Integer financeType;
    /**
     * 父类型
     */
    @TableField("pCodeType")
    private String pCodeType;

}
