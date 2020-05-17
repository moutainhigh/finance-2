package com.july.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.july.company.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 融资公司明细信息
 * @author zengxueqi
 * @since 2020-05-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_company_detail")
public class CompanyDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 注册地址
     */
    @TableField("registerAddress")
    private Integer registerAddress;
    /**
     * 融资阶段
     */
    @TableField("financeState")
    private Integer financeState;
    /**
     * 融资额度
     */
    @TableField("financeQuota")
    private Integer financeQuota;
    /**
     * 行业方向
     */
    @TableField("industryDirect")
    private Integer industryDirect;
    /**
     * 股东背景
     */
    @TableField("shareholder")
    private Integer shareholder;
    /**
     * 营业收入
     */
    @TableField("business")
    private Integer business;
    /**
     * 产品阶段
     */
    @TableField("productState")
    private Integer productState;
    /**
     * 营业收入增长率
     */
    @TableField("businessAddRate")
    private Integer businessAddRate;
    /**
     * 产品毛利率/预期产品毛利率
     */
    @TableField("productRate")
    private Integer productRate;
    /**
     * 净利率
     */
    @TableField("netInterestRate")
    private Integer netInterestRate;
    /**
     * 过往融资金额
     */
    @TableField("oldFinanceQuota")
    private Integer oldFinanceQuota;
    /**
     * 实际控制人创业经历
     */
    @TableField("experience")
    private Integer experience;
    /**
     * 员工人数
     */
    @TableField("staffCount")
    private Integer staffCount;
    /**
     * 目前市场容量
     */
    @TableField("marketCapacity")
    private Integer marketCapacity;
    /**
     * 市场增长率
     */
    @TableField("marketAddRate")
    private Integer marketAddRate;
    /**
     * 目标客户
     */
    @TableField("targetCustomer")
    private Integer targetCustomer;
    /**
     * 市场占有率/预期市场占有率
     */
    @TableField("marketOccupyRate")
    private Integer marketOccupyRate;
    /**
     * 是否接收回购
     */
    @TableField("boolBuyBack")
    private Integer boolBuyBack;
    /**
     * 发明专利数量
     */
    @TableField("patentCount")
    private Integer patentCount;
    /**
     * 公司竞争优势
     */
    @TableField("advantage")
    private String advantage;
    /**
     * 股东累计投入资金
     */
    @TableField("capitals")
    private Integer capitals;
    /**
     * 公司所获评定名称
     */
    @TableField("evaluateName")
    private Integer evaluateName;
    /**
     * 融资公司id
     */
    @TableField("companyId")
    private Long companyId;

}
