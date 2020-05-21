package com.july.company.dto.code;

import lombok.Data;

import java.io.Serializable;

/**
 * 字典信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-21 15:17
 **/
@Data
public class SysCodeDto implements Serializable {

    private static final long serialVersionUID = -4477763275431704376L;

    /**
     * 字典类型(比如地区：REGION)
     */
    private String codeType;

}
