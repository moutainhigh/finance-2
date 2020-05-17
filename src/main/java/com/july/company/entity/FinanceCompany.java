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
 * @since 2020-05-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_company")
public class FinanceCompany extends BaseEntity {

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

}
