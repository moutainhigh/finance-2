package com.july.company.dto.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 编辑页面详情展示（后台）
 * @author zengxueqi
 * @program finance
 * @since 2020-06-08 10:25
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockEditDetailDto implements Serializable {

    private static final long serialVersionUID = 7049298509118081278L;

    /**
     * 产品状态(驳回=-1，保存=0，待审核=1，已发布=2，已下线=3，已删除=4)
     */
    private Integer status;
    /**
     * 债权融资产品id
     */
    private Long productId;
    /**
     * 产品名称
     */
    private String title;
    /**
     * 机构id
     */
    private Long institutionId;

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
     * 办公地址(详细地址)
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
     * 目标市场增长率
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
     * 预计上市时间
     */
    private String timeToMarket;
    /**
     * 企业状态
     */
    private String companyStatus;

}
