package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品匹配信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-25 10:22
 **/
@Data
public class BondProductMatchDto implements Serializable {

    private static final long serialVersionUID = -8152047767416797656L;

    /**
     * 行业方向
     */
    private String IndustryDirect;
    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 专利发明数量
     */
    private String patentCount;
    /**
     * 股东背景
     */
    private String shareholder;
    /**
     * 贷款期限
     */
    private String loanTerm;
    /**
     * 贷款额度
     */
    private String loanQuota;
    /**
     * 营业收入
     */
    private String business;
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
