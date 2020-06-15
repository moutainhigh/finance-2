package com.july.company.dto.company;

import lombok.Data;

import java.io.Serializable;

/**
 * 个人中心获取企业信息（前台）
 * @author xiajunwei
 * @program finance
 * @since 2020-06-15 09:02
 **/
@Data
public class CompanyByUserDto implements Serializable {


    private static final long serialVersionUID = 1964436572037754885L;
    /**
     * 用户id
     */
    private Long userId;

}
