package com.july.company.vo.institution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 机构产品信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-26 13:26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InstitutionProductVo implements Serializable {

    private static final long serialVersionUID = 8560287755937705136L;

    /**
     * 产品名称
     */
    private String productName;

}
