package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 企业申请产品信息
 * @author zengxueqi
 * @since 2020-06-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_apply")
public class FinanceApply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 企业id
     */
    @TableField("companyId")
    private Long companyId;

    /**
     * 产品id
     */
    @TableField("productId")
    private Long productId;

    /**
     * 申请状态（0=待审核，1=已通过，2=已驳回）
     */
    @TableField("status")
    private Integer status;

    /**
     * 备注(驳回原因)
     */
    @TableField("remark")
    private String remark;

}
