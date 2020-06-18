package com.july.company.dto.company;

import lombok.Data;

import java.io.Serializable;

/**
 * 企业信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-06 23:22
 **/
@Data
public class CompanyMatchDto implements Serializable {

    private static final long serialVersionUID = -4122018021429814825L;

    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 产品类型(0=股权产品，1=债券产品)
     */
    private Integer productType;

}
