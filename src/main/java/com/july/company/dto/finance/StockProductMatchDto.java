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
public class StockProductMatchDto implements Serializable {

    private static final long serialVersionUID = -8152047767416797656L;

    /**
     * 融资额度
     */
    private String financeQuota;
    /**
     * 融资阶段
     */
    private Node financeState;
    /**
     * 行业方向
     */
    private Node industryDirect;
    /**
     * 注册地址
     */
    private Node registerAddress;
    /**
     * 营业收入
     */
    private String business;
    /**
     * 员工人数
     */
    private String staffCount;
    /**
     * 预期市场占有率
     */
    private String marketOccupyRate;
    /**
     * 公司所获评定称号
     */
    private List<Node> evaluateName;
    /**
     * 产品或机构名称
     */
    private String mechanismOrProduct;
    /**
     * 产品阶段
     */
    private Node productState;
    /**
     * 产品毛利率
     */
    private Integer productRate;
    /**
     * 实际控制人创业经历
     */
    private String experience;
    /**
     * 专利发明数量
     */
    private String patentCount;
    /**
     * 股东背景
     */
    private Node shareholder;
    /**
     * 股东累计投入资金
     */
    private String capitals;
    /**
     * 公司竞争优势
     */
    private List<Node> advantage;
    /**
     * 过往融资金额
     */
    private String oldFinanceQuota;
    /**
     * 净利率
     */
    private String netInterestRate;
    /**
     * 目标客户
     */
    private List<Node> targetCustomer;
    /**
     * 历史创业企业状态
     */
    private String companyStatus;
    /**
     * 市场容量
     */
    private String marketCapacity;
    /**
     * 市场容量预期增长率
     */
    private String marketAddRate;
    /**
     * 是否接受回购条款
     */
    private String boolBuyBack;
    /**
     * 预计上市时间
     */
    private String timeToMarket;
    /**
     * 营业收入增长率
     */
    private String businessAddRate;
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
    /**
     * 保存一键匹配json信息
     */
    private OperateMatchDto operateMatchDto;

}
