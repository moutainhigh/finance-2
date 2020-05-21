package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.base.BaseEntity;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 金融机构信息
 * @author zengxueqi
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_institution")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Institution extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 机构名称
     */
    @TableField("institutionName")
    private String institutionName;
    /**
     * 机构编码
     */
    @TableField("institutionCode")
    private String institutionCode;
    /**
     * 机构类型
     */
    @TableField("institutionType")
    private Integer institutionType;
    /**
     * 地区id
     */
    @TableField("regionId")
    private Long regionId;

}
