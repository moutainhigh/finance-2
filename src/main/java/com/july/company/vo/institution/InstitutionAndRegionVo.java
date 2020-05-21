package com.july.company.vo.institution;

import lombok.Data;

import java.io.Serializable;

/**
 * 机构和地图区域信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-21 10:33
 **/
@Data
public class InstitutionAndRegionVo implements Serializable {

    private static final long serialVersionUID = 4215278069766091938L;

    /**
     * 机构名称
     */
    private String institutionName;
    /**
     * 机构类型
     */
    private Integer institutionType;
    /**
     * 地区id
     */
    private Long regionId;

}
