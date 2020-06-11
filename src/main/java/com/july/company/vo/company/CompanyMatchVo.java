package com.july.company.vo.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 公司匹配信息
 * @author zengxueqi
 * @since 2020-05-28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyMatchVo implements Serializable {

    private static final long serialVersionUID = 7400358919328810916L;
    
    /**
     * 是否匹配(0=否,1=是)
     */
    private Integer boolMatch;

}
