package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 股权金融查询（后台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockListVo implements Serializable {


    private static final long serialVersionUID = -5670919754549727202L;
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
     * 股权融资产品id
     */
    private Integer productId;
    /**
     * 注册地址
     */
    private String registerAddressStr;

    private String registerAddress;
    /**
     * 融资阶段
     */
    private String financeStateStr;

    private String financeState;
    /**
     * 融资额度
     */
    private String financeQuotaStr;

    private String financeQuota;
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
     * 产品阶段
     */
    private String productStateStr;

    private String productState;
    /**
     * 营业收入增长率
     */
    private String businessAddRateStr;

    private String businessAddRate;
    /**
     * 产品毛利率/预期产品毛利率
     */
    private String productRateStr;

    private String productRate;
    /**
     * 净利率
     */
    private String netInterestRateStr;

    private String netInterestRate;
    /**
     * 过往融资金额
     */
    private String oldFinanceQuotaStr;

    private String oldFinanceQuota;
    /**
     * 实际控制人创业经历
     */
    private String experienceStr;

    private String experience;
    /**
     * 员工人数
     */
    private String staffCountStr;

    private String staffCount;
    /**
     * 目前市场容量
     */
    private String marketCapacityStr;

    private String marketCapacity;
    /**
     * 市场增长率
     */
    private String marketAddRateStr;

    private String marketAddRate;
    /**
     * 目标客户
     */
    private String targetCustomerStr;

    private String targetCustomer;
    /**
     * 市场占有率/预期市场占有率
     */
    private String marketOccupyRateStr;

    private String marketOccupyRate;
    /**
     * 是否接收回购
     */
    private String boolBuyBackStr;

    private String boolBuyBack;
    /**
     * 发明专利数量
     */
    private String patentCountStr;

    private String patentCount;
    /**
     * 公司竞争优势
     */
    private String advantageStr;

    private String advantage;
    /**
     * 股东累计投入资金
     */
    private String capitalsStr;

    private String capitals;
    /**
     * 公司所获评定名称
     */
    private String evaluateNameStr;

    private String evaluateName;

}
