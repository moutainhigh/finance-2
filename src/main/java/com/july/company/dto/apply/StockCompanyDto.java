package com.july.company.dto.apply;

import lombok.Data;

import java.io.Serializable;

/**
 * 企业信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-13 19:33
 **/
@Data
public class StockCompanyDto implements Serializable {

    private static final long serialVersionUID = 3481612980735464681L;

    /**
     * 企业id
     */
    private Long companyId;

}
