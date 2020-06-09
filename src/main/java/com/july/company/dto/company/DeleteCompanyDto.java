package com.july.company.dto.company;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取企业信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-07 09:02
 **/
@Data
public class DeleteCompanyDto implements Serializable {

    private static final long serialVersionUID = -2040029208842015669L;

    /**
     * 企业id
     * @mock 1, 2, 3
     */
    private String companyIds;

}
