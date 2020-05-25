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
 * 融资公司明细信息
 * @author zengxueqi
 * @since 2020-05-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_product_detail")
public class FinanceProductDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 注册地址
     */
    @TableField("registerAddress")
    private String registerAddress;
    /**
     * 融资阶段
     */
    @TableField("financeState")
    private String financeState;
    /**
     * 融资额度
     */
    @TableField("financeQuota")
    private String financeQuota;
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
     * 产品阶段
     */
    @TableField("productState")
    private String productState;
    /**
     * 营业收入增长率
     */
    @TableField("businessAddRate")
    private String businessAddRate;
    /**
     * 产品毛利率/预期产品毛利率
     */
    @TableField("productRate")
    private String productRate;
    /**
     * 净利率
     */
    @TableField("netInterestRate")
    private String netInterestRate;
    /**
     * 过往融资金额
     */
    @TableField("oldFinanceQuota")
    private String oldFinanceQuota;
    /**
     * 实际控制人创业经历
     */
    @TableField("experience")
    private String experience;
    /**
     * 员工人数
     */
    @TableField("staffCount")
    private String staffCount;
    /**
     * 目前市场容量
     */
    @TableField("marketCapacity")
    private String marketCapacity;
    /**
     * 目标市场增长率
     */
    @TableField("marketAddRate")
    private String marketAddRate;
    /**
     * 目标客户
     */
    @TableField("targetCustomer")
    private String targetCustomer;
    /**
     * 市场占有率/预期市场占有率
     */
    @TableField("marketOccupyRate")
    private String marketOccupyRate;
    /**
     * 是否接收回购
     */
    @TableField("boolBuyBack")
    private String boolBuyBack;
    /**
     * 发明专利数量
     */
    @TableField("patentCount")
    private String patentCount;
    /**
     * 公司竞争优势
     */
    @TableField("advantage")
    private String advantage;
    /**
     * 股东累计投入资金
     */
    @TableField("capitals")
    private String capitals;
    /**
     * 公司所获评定名称
     */
    @TableField("evaluateName")
    private String evaluateName;
    /**
     * 股权融资产品id
     */
    @TableField("productId")
    private Long productId;
    /**
     * 预计上市时间
     */
    @TableField("timeToMarket")
    private String timeToMarket;
    /**
     * 历史创业企业状态
     */
    @TableField("companyStatus")
    private String companyStatus;
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
     * 净利率
     */
    @TableField("jlr")
    private String jlr;

}
