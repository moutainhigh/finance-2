package com.july.company.dto.company;

import lombok.Data;

import java.io.Serializable;

/**
 * 公司密码信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-15 15:14
 **/
@Data
public class CompanyPasswordDto implements Serializable {

    private static final long serialVersionUID = 1520913942458340423L;

    /**
     * 公司id
     */
    private Long companyId;

}
