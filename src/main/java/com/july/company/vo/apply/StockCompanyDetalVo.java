package com.july.company.vo.apply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 企业信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-12 13:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockCompanyDetalVo implements Serializable {

    private static final long serialVersionUID = -55679396054802321L;

    /**
     * 企业名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系方式
     */
    private String tel;
    /**
     * 注册地址
     */
    private String registerAddress;
    /**
     * 办公地址
     */
    private String workAddress;
    /**
     * 公司简介
     */
    private String introduce;
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
     * 市场增长率
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
     * 历史企业状态
     */
    private String companyStatus;
    /**
     * 预计上市时间
     */
    private String timeToMarket;

}
