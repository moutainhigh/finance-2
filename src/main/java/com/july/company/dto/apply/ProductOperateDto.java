package com.july.company.dto.apply;

import lombok.Data;

import java.io.Serializable;

/**
 * 产品操作信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-12 14:27
 **/
@Data
public class ProductOperateDto implements Serializable {

    private static final long serialVersionUID = -1776364229792512893L;

    /**
     * 申请产品id
     */
    private Long applyId;
    /**
     * 操作类型(1=通过，2=驳回)
     */
    private Integer operateType;
    /**
     * 驳回原因
     */
    private String remark;

}
