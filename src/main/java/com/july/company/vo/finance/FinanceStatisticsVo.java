package com.july.company.vo.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 融资统计信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-21 17:04
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinanceStatisticsVo implements Serializable {

    private static final long serialVersionUID = 269159652118189115L;

    /**
     * 企业入驻数量
     */
    private Integer companyCount;
    /**
     * 机构入驻数量
     */
    private Integer institutionCount;
    /**
     * 股权产品数量
     */
    private Integer stockCount;
    /**
     * 债券产品数量
     */
    private Integer bondCount;
    /**
     * 匹配数量
     */
    private Integer matchCount;
    /**
     * 匹配金额
     */
    private Double matchMoney;

}
