package com.july.company.dto.apply;

import lombok.Data;

import java.io.Serializable;

/**
 * 公司信息
 * @author xiajunwei
 * @program apply
 * @since 2020-06-15 19:33
 **/
@Data
public class CompanyApplyDto implements Serializable {


    private static final long serialVersionUID = 1834300867197871823L;
    /**
     * 公司id
     */
    private Long companyId;
}
