package com.july.company.vo.institution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 机构和地图区域信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-21 10:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstitutionAndRegionVo implements Serializable {

    private static final long serialVersionUID = 4215278069766091938L;

    /**
     * 机构id
     */
    private Long id;
    /**
     * 机构名称
     */
    private String institutionName;
    /**
     * 机构类型(0=股权机构，1=债券机构)
     * @mock 1
     */
    private Integer institutionType;
    /**
     * 地区id
     */
    private String regionCode;
    /**
     * 地区名称
     */
    private String regionName;

}
