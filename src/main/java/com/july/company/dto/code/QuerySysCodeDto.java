package com.july.company.dto.code;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询条件信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-25 18:09
 **/
@Data
public class QuerySysCodeDto implements Serializable {

    private static final long serialVersionUID = 7144057263537435477L;

    /**
     * 融资类型(0=股权融资，1=债券融资)
     * @mock 0
     */
    private Integer financeType;

}
