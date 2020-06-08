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
     * 注册地址
     */
    private Integer productId;
    /**
     * 股权融资产品id
     */
    private String registerAddressStr;
    /**
     * 融资阶段
     */
    private String financeStateStr;
    /**
     * 融资额度
     */
    private String financeQuotaStr;
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
     * 产品阶段
     */
    private String productStateStr;
    /**
     * 营业收入增长率
     */
    private String businessAddRateStr;
    /**
     * 产品毛利率/预期产品毛利率
     */
    private String productRateStr;
    /**
     * 净利率
     */
    private String netInterestRateStr;
    /**
     * 过往融资金额
     */
    private String oldFinanceQuotaStr;
    /**
     * 实际控制人创业经历
     */
    private String experienceStr;
    /**
     * 员工人数
     */
    private String staffCountStr;
    /**
     * 目前市场容量
     */
    private String marketCapacityStr;
    /**
     * 市场增长率
     */
    private String marketAddRateStr;
    /**
     * 目标客户
     */
    private String targetCustomerStr;
    /**
     * 市场占有率/预期市场占有率
     */
    private String marketOccupyRateStr;
    /**
     * 是否接收回购
     */
    private String boolBuyBackStr;
    /**
     * 发明专利数量
     */
    private String patentCountStr;
    /**
     * 公司竞争优势
     */
    private String advantageStr;
    /**
     * 股东累计投入资金
     */
    private String capitalsStr;
    /**
     * 公司所获评定名称
     */
    private String evaluateNameStr;

}
