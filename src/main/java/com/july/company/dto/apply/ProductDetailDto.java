package com.july.company.dto.apply;

import lombok.Data;

import java.io.Serializable;

/**
 * 申请信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-12 13:06
 **/
@Data
public class ProductDetailDto implements Serializable {

    private static final long serialVersionUID = -3833517506066155498L;

    /**
     * 申请产品id
     */
    private Long applyId;

}
