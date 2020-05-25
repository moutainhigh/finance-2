package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 股权公司信息查询
 * @author zengxueqi
 * @program finance
 * @since 2020-05-17 10:25
 **/
@Data
public class FinanceProductDto implements Serializable {

    private static final long serialVersionUID = 6063770983542170577L;

    /**
     * 融资额度
     */
    private String financeQuota;
    /**
     * 融资阶段
     */
    private String financeState;
    /**
     * 行业方向
     */
    private String IndustryDirect;
    /**
     * 注册地址
     */
    private String registerAddress;
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
    private String evaluateName;
    /**
     * 产品或机构名称
     */
    private String mechanismOrProduct;
    /**
     * 产品阶段
     */
    private String productState;
    /**
     * 产品毛利率
     */
    private String productRate;
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
    private String shareholder;
    /**
     * 股东累计投入资金
     */
    private String capitals;
    /**
     * 公司竞争优势
     */
    private Integer advantage;
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
    private String targetCustomer;
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
     * 排序字段(0=融资金额，1=融资阶段)
     * @mock 1
     */
    private Integer orderByField;
    /**
     * 产品ids(逗号分隔)
     */
    private String productIds;

}
