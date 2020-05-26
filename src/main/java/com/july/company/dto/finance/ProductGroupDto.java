package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品分组数量信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-26 14:02
 **/
@Data
public class ProductGroupDto implements Serializable {

    private static final long serialVersionUID = -7729040825183304352L;

    /**
     * 融资机构类型(0=股权融资,1=债券融资)
     */
    private Integer financeType;
    /**
     * 产品数量
     */
    private Integer productCount;

}
