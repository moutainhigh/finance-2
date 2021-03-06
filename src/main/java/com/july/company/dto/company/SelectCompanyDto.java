package com.july.company.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取企业信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-07 09:02
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectCompanyDto implements Serializable {

    private static final long serialVersionUID = -1897504905058258089L;

    /**
     * 企业id
     */
    private Long companyId;

}
