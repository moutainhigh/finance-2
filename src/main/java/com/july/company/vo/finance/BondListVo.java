package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 债权金融查询（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BondListVo implements Serializable {


    private static final long serialVersionUID = 5843703271288489294L;
    /**
     * 股权融资产品id
     */
    private Long id;
    /**
     * 股权融资标题
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;
    /**
     * 办公地址
     */
    private String workAddress;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 产品简介
     */
    private String introduce;
    /**
     * 债权融资产品id
     */
    private Integer productId;
    /**
     * 注册地址
     */
    private String registerAddressStr;

    private String registerAddress;
    /**
     * 行业方向
     */
    private String industryDirectStr;

    private String industryDirect;
    /**
     * 股东背景
     */
    private String shareholderStr;

    private String shareholder;
    /**
     * 营业收入
     */
    private String businessStr;

    private String business;
    /**
     * 发明专利数量
     */
    private String patentCountStr;

    private String patentCount;
    /**
     * 贷款期限
     */
    private String loanTermStr;

    private String loanTerm;
    /**
     * 贷款额度
     */
    private String loanQuotaStr;

    private String loanQuota;
    /**
     * 增信方式
     */
    private String creditTypeStr;

    private String creditType;
    /**
     * 房产抵押
     */
    private String houseMortgageStr;

    private String houseMortgage;
    /**
     * 现金流
     */
    private String cashFlowStr;

    private String cashFlow;
    /**
     * 政府订单额
     */
    private String goverOrderAmountStr;

    private String goverOrderAmount;
    /**
     * 国企订单额
     */
    private String nationOrderAmountStr;

    private String nationOrderAmount;
    /**
     * 资产总额
     */
    private String assetAmountStr;

    private String assetAmount;
    /**
     * 负债总额
     */
    private String liabilitiesAmountStr;

    private String liabilitiesAmount;
    /**
     * 所有者权益
     */
    private String ownerStr;

    private String owner;
    /**
     * 企业资质
     */
    private String qualificationStr;

    private String qualification;
    /**
     * 本年度政府补贴
     */
    private String subsidyStr;

    private String subsidy;
    /**
     * 是否引入股权投资
     */
    private String boolIntroduceStr;

    private String boolIntroduce;
    /**
     * 纳税额度
     */
    private String taxAmountStr;

    private String taxAmount;
    /**
     * 是否有其他贷款
     */
    private String boolLoanStr;

    private String boolLoan;
    /**
     * 现有贷款金额
     */
    private String existAmountStr;

    private String existAmount;
    /**
     * 现有贷款金额
     */
    private String jlrStr;

    private String jlr;
    /**
     * 资产负债率
     */
    private String debtRatioStr;

    private String debtRatio;
    /**
     * 净资产收益率
     */
    private String yieldStr;

    private String yield;
    /**
     * 上一年度政府补贴
     */
    private String lastSubsidyStr;

    private String lastSubsidy;
}
