package com.july.company.dto.institution;

import lombok.Data;

import java.io.Serializable;

/**
 * 机构信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-21 10:08
 **/
@Data
public class SaveInstitutionDto implements Serializable {

    private static final long serialVersionUID = -7758729826518797436L;

    /**
     * 机构名称
     */
    private String institutionName;
    /**
     * 机构编码
     */
    private String institutionCode;
    /**
     * 机构类型
     */
    private Integer institutionType;

}
