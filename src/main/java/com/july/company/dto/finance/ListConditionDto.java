package com.july.company.dto.finance;

import lombok.Data;

import java.io.Serializable;

/**
 * 股权债权信息查询条件
 * @author zengxueqi
 * @program finance
 * @since 2020-06-04 16:58
 **/
@Data
public class ListConditionDto implements Serializable {


    private static final long serialVersionUID = -4325862433210768997L;
    /**
     * 股权融资标题
     */
    private String title;
    /**
     * 所属机构
     */
    private String mechanism;

}
