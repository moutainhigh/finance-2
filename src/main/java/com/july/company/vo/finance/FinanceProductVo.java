package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 股权融资公司信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-17 10:31
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceProductVo {

    /**
     * 股权融资标题
     * @mock 物联网投资基金
     */
    private String title;
    /**
     * 所属机构
     * @mock 红杉资本
     */
    private String mechanism;
    /**
     * 行业方向
     * @ignore
     */
    private Integer industryDirect;
    /**
     * 融资额度
     * @ignore
     */
    private Integer financeQuota;
    /**
     * 融资阶段
     * @ignore
     */
    private Integer financeState;
    /**
     * 行业方向(转换后)
     * @mock 新经济
     */
    private String industryDirectStr;
    /**
     * 融资额度(转换后)
     * @mock 500万-1000万
     */
    private String financeQuotaStr;
    /**
     * 融资阶段(转换后)
     * @mock Pre-A轮
     */
    private String financeStateStr;

}
