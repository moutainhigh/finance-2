package com.july.company.dto.company;

import lombok.Data;

import java.io.Serializable;

/**
 * 某个产品信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-04 16:58
 **/
@Data
public class CompanyIdsDto implements Serializable {


    private static final long serialVersionUID = 100763189920590826L;
    /**
     * 企业ID
     */
    private String companyIds;

}
