package com.july.company.vo.apply;

import lombok.Data;

import java.io.Serializable;

/**
 * 申请信息
 * @author zengxueqi
 * @program finance
 * @since 2020-06-12 13:06
 **/
@Data
public class ProductDetalVo implements Serializable {

    private static final long serialVersionUID = -55679396054802321L;

    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 融资类型(转换后)
     */
    private Integer financeType;
    private String financeTypeStr;
    /**
     * 产品所属机构
     */
    private String mechanism;
    /**
     * 申请时间(转换后)
     */
    private Long createdTime;
    private String createdTimeStr;
    /**
     * 当前状态
     */
    private Integer status;
    private String statusStr;
    /**
     * 备注
     */
    private String remark;

}
