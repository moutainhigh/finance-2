package com.july.company.vo.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 注册的公司信息
 * @author xia.junwei
 * @since 2020-05-28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyVo implements Serializable {

    private static final long serialVersionUID = -8194209243144086586L;

    /**
     * 公司id
     */
    private Long id;
    /**
     * 入住企业名称
     */
    private String companyName;
    /**
     * 统一信用代码
     */
    private String creditCode;
    /**
     * 注册时间
     */
    private String registerTime;
    /**
     * 注册地
     */
    private String registerAddress;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 企业状态(0=正常,1=禁用)
     */
    private Integer status;

}
