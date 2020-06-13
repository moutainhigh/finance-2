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
 * 注册的公司信息
 * @author zengxueqi
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_company")
public class Company extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 入住企业名称
     */
    @TableField("companyName")
    private String companyName;

    /**
     * 统一信用代码
     */
    @TableField("creditCode")
    private String creditCode;

    /**
     * 企业状态(0=正常,1=禁用)
     */
    @TableField("status")
    private Integer status;

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
     * 联系方式
     */
    @TableField("tel")
    private String tel;
    /**
     * 公司介绍
     */
    @TableField("introduce")
    private String introduce;
    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;
    /**
     * 详细地址
     */
    @TableField("detailAddress")
    private String detailAddress;

}
