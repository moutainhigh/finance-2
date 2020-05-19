package com.july.company.dto.login;

import lombok.Data;

import java.io.Serializable;

/**
 * 忘记密码信息
 * @author zengxueqi
 * @program finance
 * @since 2020-05-19 15:22
 **/
@Data
public class UserInfoValidDto implements Serializable {

    private static final long serialVersionUID = 5366052483273769965L;

    /**
     * 手机号
     */
    private String mobile;

}
