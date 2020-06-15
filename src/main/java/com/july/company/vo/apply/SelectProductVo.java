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
public class SelectProductVo implements Serializable {

    private static final long serialVersionUID = -55679396054802321L;

    /**
     * 产品id
     */
    private Long applyId;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 融资类型
     */
    private Integer financeType;
    /**
     * 融资类型(转换后)
     */
    private String financeTypeStr;
    /**
     * 产品所属机构
     */
    private String mechanism;
    /**
     * 申请时间
     */
    private Long createdTime;
    /**
     * 申请时间(转换后)
     */
    private String createdTimeStr;
    /**
     * 当前状态
     */
    private Integer status;

}
