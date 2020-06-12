package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 编辑页面详情展示（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BondEditDetailVo implements Serializable {


    private static final long serialVersionUID = 7680189733243514257L;
    /**
     * 债权融资产品id
     */
    private Long id;
    /**
     * 产品名称
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;

    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 办公地址
     */
    private String workAddress;
    /**
     * 办公地址（详细地址）
     */
    private String detailAddress;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 产品简介
     */
    private String introduce;
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
    private String industryDirect;
    /**
     * 股东背景
     */
    private String shareholder;
    /**
     * 增信方式
     */
    private String creditType;
    /**
     * 是否接受房产抵押
     */
    private String houseMortgage;
    /**
     * 营业收入
     */
    private String business;
    /**
     * 净利润
     */
    private String jlr;
    /**
     * 上年度经营活动现金流净额
     */
    private String cashFlow;
    /**
     * 政府订单额
     */
    private String goverOrderAmount;
    /**
     * 央企/大型国企核心供应商订单额
     */
    private String nationOrderAmount;
    /**
     * 资产总额（万元）
     */
    private String assetAmount;
    /**
     * 负债总额（万元）
     */
    private String liabilitiesAmount;
    /**
     * 所有者权益（万元）
     */
    private String owner;
    /**
     * 企业资质
     */
    private String qualification;
    /**
     * 本年度政府补贴
     */
    private String subsidy;
    /**
     * 上一年度政府补贴
     */
    private String lastSubsidy;
    /**
     * 是否已引入股权投资
     */
    private String boolIntroduce;
    /**
     * 纳税额度
     */
    private String taxAmount;
    /**
     * 发明专利数量
     */
    private String patentCount;
    /**
     * 是否有其他贷款
     */
    private String boolLoan;
    /**
     * 现有贷款金额
     */
    private String existAmount;


}
