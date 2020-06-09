package com.july.company.dto.finance;

import com.july.company.dto.Node;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
     * 办公地址
     */
    private String workAddress;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 公司介绍
     */
    private String introduce;
    /**
     * 行业方向
     */
    private List<Node> industryDirect;
    /**
     * 注册地址
     */
    private Node registerAddress;
    /**
     * 专利发明数量
     */
    private String patentCount;
    /**
     * 股东背景
     */
    private List<Node> shareholder;
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
    private List<Node> creditType;
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
    private List<Node> qualification;
    /**
     * 本年度政府补贴
     */
    private String subsidy;
    /**
     * 上一年度年度政府补贴
     */
    private String lastSubsidy;
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
    private List<Node> boolLoan;
    /**
     * 现有贷款金额
     */
    private String existAmount;
    /**
     * 净利率
     */
    private String jlr;
    /**
     * 资产负债率
     */
    private String debtRatio;
    /**
     * 净资产收益率
     */
    private String yield;
    /**
     * 保存一键匹配json信息
     */
    private OperateMatchDto operateMatchDto;
}
