package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 某个产品信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-04 16:58
 **/
@Data
public class OneProductDto implements Serializable {

    private static final long serialVersionUID = 7059815747160280511L;

    /**
     * 产品id
     */
    private String productIds;

}
