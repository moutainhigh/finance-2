package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 债券融资详细信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-26 09:40
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceBondProductDetailVo implements Serializable {

    private static final long serialVersionUID = -3939031125822945927L;

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
     * 注册地址
     */
    private String registerAddressStr;
    /**
     * 贷款期限
     */
    private String loanTerm;
    /**
     * 贷款额度
     */
    private String loanQuota;
    /**
     * 行业方向
     */
    private String industryDirectStr;
    /**
     * 股东背景
     */
    private String shareholderStr;
    /**
     * 营业收入
     */
    private String businessStr;
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
     * 发明专利数量
     */
    private String patentCountStr;
    /**
     * 是否有其他贷款
     */
    private String boolLoan;
    /**
     * 现有贷款金额
     */
    private String existAmount;
    /**
     * 净利润
     */
    private String jlr;
    /**
     * 资产负债率
     */
    private String debtRatio;
    /**
     * 资产收益率
     */
    private String yield;

}
