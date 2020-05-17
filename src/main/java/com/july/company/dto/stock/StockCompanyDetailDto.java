package com.july.company.dto.stock;

import lombok.Data;

import java.io.Serializable;

/**
 * 股权公司明细信息查询
 * @author zengxueqi
 * @program finance
 * @since 2020-05-17 10:25
 **/
@Data
public class StockCompanyDetailDto implements Serializable {

    private static final long serialVersionUID = 6063770983542170577L;

    /**
     * 融资公司id
     */
    private Long companyId;

}
