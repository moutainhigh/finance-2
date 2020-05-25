package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-25 10:49
 **/
@Data
public class ProductInfoDto implements Serializable {

    private static final long serialVersionUID = -2723196261427880522L;

    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 融资阶段
     */
    private String financeState;
    /**
     * 融资额度
     */
    private String financeQuota;
    /**
     * 行业方向
     */
    private String industryDirect;
    /**
     * 股东背景
     */
    private String shareholder;
    /**
     * 营业收入
     */
    private String business;
    /**
     * 产品阶段
     */
    private String productState;
    /**
     * 营业收入增长率
     */
    private String businessAddRate;
    /**
     * 产品毛利率/预期产品毛利率
     */
    private String productRate;
    /**
     * 净利率
     */
    private String netInterestRate;
    /**
     * 过往融资金额
     */
    private String oldFinanceQuota;
    /**
     * 实际控制人创业经历
     */
    private String experience;
    /**
     * 员工人数
     */
    private String staffCount;
    /**
     * 目前市场容量
     */
    private String marketCapacity;
    /**
     * 目标市场增长率
     */
    private String marketAddRate;
    /**
     * 目标客户
     */
    private String targetCustomer;
    /**
     * 市场占有率/预期市场占有率
     */
    private String marketOccupyRate;
    /**
     * 是否接收回购
     */
    private String boolBuyBack;
    /**
     * 发明专利数量
     */
    private String patentCount;
    /**
     * 公司竞争优势
     */
    private String advantage;
    /**
     * 股东累计投入资金
     */
    private String capitals;
    /**
     * 公司所获评定名称
     */
    private String evaluateName;
    /**
     * 股权融资产品id
     */
    private Long productId;
    /**
     * 预计上市时间
     */
    private String timeToMarket;
    /**
     * 历史创业企业状态
     */
    private String companyStatus;
    /**
     * 产品名称
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;


    /**
     * 贷款期限
     */
    private String loanTerm;
    /**
     * 贷款额度
     */
    private String loanQuota;
    /**
     * 增信方式
     */
    private String creditType;
    /**
     * 房产抵押
     */
    private String houseMortgage;
    /**
     * 现金流
     */
    private String cashFlow;
    /**
     * 政府订单额
     */
    private String goverOrderAmount;
    /**
     * 国企订单额
     */
    private String nationOrderAmount;
    /**
     * 贷款期限
     */
    private String termLoan;
    /**
     * 资产总额
     */
    private String assetAmount;
    /**
     * 负债总额
     */
    private String liabilitiesAmount;
    /**
     * 所有者权益
     */
    private String owner;
    /**
     * 企业资质
     */
    private String qualification;
    /**
     * 政府补贴
     */
    private String subsidy;
    /**
     * 是否引入股权投资
     */
    private String boolIntroduce;
    /**
     * 纳税额度
     */
    private String taxAmount;
    /**
     * 是否有其他贷款
     */
    private String boolLoan;
    /**
     * 现有贷款金额
     */
    private String existAmount;
    /**
     * 净利率
     */
    private String jlr;

}
