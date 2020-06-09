package com.july.company.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * 融资公司明细信息
 * @author zengxueqi
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_bond_detail")
@Builder
public class FinanceBondDetail extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股权融资产品id
     */
    @TableField("productId")
    private Long productId;

    /**
     * 注册地址
     */
    @TableField("registerAddress")
    private String registerAddress;

    /**
     * 行业方向
     */
    @TableField("industryDirect")
    private String industryDirect;

    /**
     * 股东背景
     */
    @TableField("shareholder")
    private String shareholder;

    /**
     * 营业收入
     */
    @TableField("business")
    private String business;

    /**
     * 发明专利数量
     */
    @TableField("patentCount")
    private String patentCount;

    /**
     * 贷款期限
     */
    @TableField("loanTerm")
    private String loanTerm;

    /**
     * 贷款额度
     */
    @TableField("loanQuota")
    private String loanQuota;

    /**
     * 增信方式
     */
    @TableField("creditType")
    private String creditType;

    /**
     * 房产抵押
     */
    @TableField("houseMortgage")
    private String houseMortgage;

    /**
     * 现金流
     */
    @TableField("cashFlow")
    private String cashFlow;

    /**
     * 政府订单额
     */
    @TableField("goverOrderAmount")
    private String goverOrderAmount;

    /**
     * 国企订单额
     */
    @TableField("nationOrderAmount")
    private String nationOrderAmount;

    /**
     * 贷款期限
     */
    @TableField("termLoan")
    private String termLoan;

    /**
     * 资产总额
     */
    @TableField("assetAmount")
    private String assetAmount;

    /**
     * 负债总额
     */
    @TableField("liabilitiesAmount")
    private String liabilitiesAmount;

    /**
     * 所有者权益
     */
    @TableField("owner")
    private String owner;

    /**
     * 企业资质
     */
    @TableField("qualification")
    private String qualification;

    /**
     * 政府补贴
     */
    @TableField("subsidy")
    private String subsidy;

    /**
     * 是否引入股权投资
     */
    @TableField("boolIntroduce")
    private String boolIntroduce;

    /**
     * 纳税额度
     */
    @TableField("taxAmount")
    private String taxAmount;

    /**
     * 是否有其他贷款
     */
    @TableField("boolLoan")
    private String boolLoan;

    /**
     * 现有贷款金额
     */
    @TableField("existAmount")
    private String existAmount;

    /**
     * 净利润
     */
    @TableField("jlr")
    private String jlr;

    /**
     * 资产负债率
     */
    @TableField("debtRatio")
    private String debtRatio;

    /**
     * 资产收益率
     */
    @TableField("yield")
    private String yield;
    /**
     * 上一年度政府补贴
     */
    @TableField("lastSubsidy")
    private String lastSubsidy;


}
