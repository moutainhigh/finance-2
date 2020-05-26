package com.july.company.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.july.company.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * 融资公司明细信息
 * @author zengxueqi
 * @since 2020-05-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fc_finance_stock_detail")
public class FinanceStockDetail extends BaseEntity{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 股权融资产品id
     */
    @TableField("productId")
    private Long productId;

    /**
     * 注册地址
     */
    @TableField("registerAddress")
    private String registerAddress;

    /**
     * 融资阶段
     */
    @TableField("financeState")
    private String financeState;

    /**
     * 融资额度
     */
    @TableField("financeQuota")
    private String financeQuota;

    /**
     * 行业方向
     */
    @TableField("industryDirect")
    private String industryDirect;

    /**
     * 股东背景
     */
    @TableField("shareholder")
    private String shareholder;

    /**
     * 营业收入
     */
    @TableField("business")
    private String business;

    /**
     * 产品阶段
     */
    @TableField("productState")
    private String productState;

    /**
     * 营业收入增长率
     */
    @TableField("businessAddRate")
    private String businessAddRate;

    /**
     * 产品毛利率/预期产品毛利率
     */
    @TableField("productRate")
    private String productRate;

    /**
     * 净利率
     */
    @TableField("netInterestRate")
    private String netInterestRate;

    /**
     * 过往融资金额
     */
    @TableField("oldFinanceQuota")
    private String oldFinanceQuota;

    /**
     * 实际控制人创业经历
     */
    @TableField("experience")
    private String experience;

    /**
     * 员工人数
     */
    @TableField("staffCount")
    private String staffCount;

    /**
     * 目前市场容量
     */
    @TableField("marketCapacity")
    private String marketCapacity;

    /**
     * 目标市场增长率
     */
    @TableField("marketAddRate")
    private String marketAddRate;

    /**
     * 目标客户
     */
    @TableField("targetCustomer")
    private String targetCustomer;

    /**
     * 市场占有率/预期市场占有率
     */
    @TableField("marketOccupyRate")
    private String marketOccupyRate;

    /**
     * 是否接收回购
     */
    @TableField("boolBuyBack")
    private String boolBuyBack;

    /**
     * 发明专利数量
     */
    @TableField("patentCount")
    private String patentCount;

    /**
     * 公司竞争优势
     */
    @TableField("advantage")
    private String advantage;

    /**
     * 股东累计投入资金
     */
    @TableField("capitals")
    private String capitals;

    /**
     * 公司所获评定名称
     */
    @TableField("evaluateName")
    private String evaluateName;

    /**
     * 预计上市时间
     */
    @TableField("timeToMarket")
    private String timeToMarket;

    /**
     * 企业状态
     */
    @TableField("companyStatus")
    private String companyStatus;

}
