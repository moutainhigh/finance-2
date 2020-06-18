package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 债券融资一键匹配
 * @author zengxueqi
 * @since 2020-06-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_bond_match")
public class FinanceBondMatch extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股权融资产品id
     */
    @TableField("detailId")
    private Long detailId;

    /**
     * 上一年度政府补贴
     */
    @TableField("lastSubsidy")
    private String lastSubsidy;

    /**
     * 负债总额
     */
    @TableField("liabilitiesAmount")
    private String liabilitiesAmount;

    /**
     * 资产总额
     */
    @TableField("assetAmount")
    private String assetAmount;

    /**
     * 所有者权益
     */
    @TableField("owner")
    private String owner;

    /**
     * 企业id
     */
    @TableField("companyId")
    private Long companyId;

    /**
     * 选择类型（0：保存，1：匹配）
     */
    @TableField("chooseType")
    private Integer chooseType;

}
