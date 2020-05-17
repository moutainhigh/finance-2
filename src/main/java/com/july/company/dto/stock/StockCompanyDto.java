package com.july.company.dto.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * 股权公司信息查询
 * @author zengxueqi
 * @program finance
 * @since 2020-05-17 10:25
 **/
@Data
public class StockCompanyDto implements Serializable {

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
    private Integer staffCount;
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

}
