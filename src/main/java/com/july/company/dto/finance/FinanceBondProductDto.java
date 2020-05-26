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
public class FinanceBondProductDto implements Serializable {

    private static final long serialVersionUID = 6063770983542170577L;

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
     * 企业资质
     */
    private String qualification;
    /**
     * 政府补贴
     */
    private String subsidy;
    /**
     * 排序字段(0=融资金额，1=融资阶段)
     * @mock 1
     */
    private Integer orderByField;
    /**
     * 产品或机构名称
     */
    private String mechanismOrProduct;

}
