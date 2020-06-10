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
public class ListInstitutionDto implements Serializable {

    private static final long serialVersionUID = -2988875244173153225L;

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
    /**
     * 所属地区
     */
    private String regionId;

}
