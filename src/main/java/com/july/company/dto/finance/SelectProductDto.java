package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-11 16:32
 **/
@Data
public class SelectProductDto implements Serializable {

    private static final long serialVersionUID = 584244606650492531L;

    /**
     * 产品id
     */
    private Long productId;

}
