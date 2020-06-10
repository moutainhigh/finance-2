package com.july.company.vo.institution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 机构信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-10 09:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListInstitutionVo implements Serializable {

    private static final long serialVersionUID = 607781739086507269L;

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
    private String institutionType;
    /**
     * 所属地区
     */
    private String regionName;

}
