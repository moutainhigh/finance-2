package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 股权产品明细信息查询
 * @author zengxueqi
 * @program finance
 * @since 2020-05-17 10:25
 **/
@Data
public class FinanceProductDetailDto implements Serializable {

    private static final long serialVersionUID = 6063770983542170577L;

    /**
     * 融资公司id
     */
    private Long companyId;

}
