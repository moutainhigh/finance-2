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
 * 融资公司信息
 * @author zengxueqi
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_product")
public class FinanceProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 股权融资标题
     */
    @TableField("title")
    private String title;
    /**
     * 所属机构
     */
    @TableField("mechanism")
    private String mechanism;
    /**
     * 注册地址
     */
    @TableField("registerAddress")
    private String registerAddress;
    /**
     * 办公地址
     */
    @TableField("workAddress")
    private String workAddress;
    /**
     * 联系电话
     */
    @TableField("tel")
    private String tel;
    /**
     * 产品介绍
     */
    @TableField("introduce")
    private String introduce;
    /**
     * 融资类型(股权融资=0，债券融资=1)
     */
    @TableField("financeType")
    private Integer financeType;
    /**
     * 机构id
     */
    @TableField("institutionId")
    private Long institutionId;
    /**
     * 产品状态(驳回=-1，保存=0，待审核=1，已发布=2，已下线=3，已删除=4)
     */
    @TableField("status")
    private Integer status;

}
