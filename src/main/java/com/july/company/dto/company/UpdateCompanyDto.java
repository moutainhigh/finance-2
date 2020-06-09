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
public class UpdateCompanyDto implements Serializable {

    private static final long serialVersionUID = -3747717632024550471L;

    /**
     * 企业id
     */
    private Long companyId;
    /**
     * 入住企业名称
     */
    private String companyName;
    /**
     * 统一信用代码
     */
    private String creditCode;
    /**
     * 企业状态(on=开启)
     * @mock on
     */
    private String status;

}
