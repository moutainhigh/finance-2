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
public class CompanyDto implements Serializable {

    private static final long serialVersionUID = -8515590324990721723L;

    /**
     * 入住企业名称
     */
    private String companyName;
    /**
     * 统一信用代码
     */
    private String creditCode;

}
