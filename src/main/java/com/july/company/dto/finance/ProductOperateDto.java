package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品操作信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-13 17:26
 **/
@Data
public class ProductOperateDto implements Serializable {

    private static final long serialVersionUID = -936989018415374913L;

    /**
     * 产品id
     */
    private Long productId;
    /**
     * 操作类型
     */
    private Integer operateType;
    /**
     * 备注
     */
    private String remark;

}
